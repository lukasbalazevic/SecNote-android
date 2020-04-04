package app.vut.secnote.data.remote

import app.vut.secnote.authservice.AuthServiceCoroutineGrpc
import app.vut.secnote.data.model.error.AppError
import app.vut.secnote.data.model.error.InvalidCredentialsError
import app.vut.secnote.data.model.error.NoConnectionError
import app.vut.secnote.data.model.error.UnknownAppError
import app.vut.secnote.data.model.error.UserNotFoundError
import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import com.github.marcoferrer.krotoplus.coroutines.withCoroutineContext
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.AbstractStub
import io.grpc.stub.MetadataUtils
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
            throw convertStatusRuntimeException(e)
        } catch (e: KotlinNullPointerException) {
            throw UnknownAppError(e)
        } catch (e: UnknownHostException) {
            throw NoConnectionError(e)
        } catch (e: InvalidCredentialsError) {
            throw e
        } catch (e: Exception) {
            throw UnknownAppError(e)
        }
    }

    private fun convertStatusRuntimeException(e: StatusRuntimeException): AppError = when (e.status.code) {
        Status.Code.OK,
        Status.Code.CANCELLED,
        Status.Code.UNKNOWN,
        Status.Code.INVALID_ARGUMENT,
        Status.Code.DEADLINE_EXCEEDED,
        Status.Code.ALREADY_EXISTS,
        Status.Code.PERMISSION_DENIED,
        Status.Code.RESOURCE_EXHAUSTED,
        Status.Code.FAILED_PRECONDITION,
        Status.Code.ABORTED,
        Status.Code.OUT_OF_RANGE,
        Status.Code.UNIMPLEMENTED,
        Status.Code.INTERNAL,
        Status.Code.UNAVAILABLE,
        Status.Code.DATA_LOSS -> UnknownAppError(e)
        Status.Code.UNAUTHENTICATED -> InvalidCredentialsError(e)
        Status.Code.NOT_FOUND -> UserNotFoundError(e)
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
