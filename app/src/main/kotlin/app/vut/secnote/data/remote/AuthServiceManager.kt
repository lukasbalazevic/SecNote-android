package app.vut.secnote.data.remote

import app.vut.secnote.authservice.AuthServiceCoroutineGrpc
import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import com.github.marcoferrer.krotoplus.coroutines.withCoroutineContext
import io.grpc.Metadata
import io.grpc.StatusRuntimeException
import io.grpc.stub.AbstractStub
import io.grpc.stub.MetadataUtils
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class AuthServiceManager @Inject constructor(
    private val client: AuthServiceCoroutineGrpc.AuthServiceCoroutineStub,
    private val tokenStore: TokenStore,
    private val cryptoHelper: CryptoHelper
) {

    suspend fun signIn(email: String, password: String, key: String) = executeApiCall {
        client.withCoroutineContext().signIn {
            this.email = email
            this.password = password
            this.key = key
        }
    }

    suspend fun signUp(email: String, password: String, key: String) = executeApiCall {
        client
            .withCoroutineContext().signUp {
                this.email = email
                this.password = password
                this.key = key
            }
    }

    suspend fun signOut() = executeApiCall {
        client
            .executeWithMetadata("")
            .withCoroutineContext().signOut {}
    }

    suspend fun renewToken() = executeApiCall {
        val credResponse = executeApiCall {
            client.withCoroutineContext().renewToken {
                refreshToken = tokenStore.getRefreshToken()
            }
        }
        tokenStore.run {
            saveAccessToken(credResponse.jwt.accessToken) && saveRefreshToken(credResponse.jwt.refreshToken)
        }
    }

    private suspend fun <T> executeApiCall(apiCall: suspend () -> T): T {
        return try {
            apiCall()
        } catch (e: StatusRuntimeException) {
            throw e
        } catch (e: KotlinNullPointerException) {
            throw e
        } catch (e: UnknownHostException) {
            Timber.e(e)
            throw e
        } catch (e: Exception) {
            Timber.e(e)
            throw e
        }
    }

    suspend fun <T : AbstractStub<T>> T.executeWithMetadata(request: String): T = executeApiCall {

        val signature = cryptoHelper.signAndEncodeDataBase64(request.toByteArray())
        val encodedMessage = cryptoHelper.encodeBase64(request.toByteArray())

        val header = Metadata()
        header.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), tokenStore.getAccessToken())
        header.put(Metadata.Key.of("Digest", Metadata.ASCII_STRING_MARSHALLER), encodedMessage)
        header.put(Metadata.Key.of("Signature", Metadata.ASCII_STRING_MARSHALLER), signature)

        MetadataUtils.attachHeaders(
            this,
            header
        )
    }
}