package app.vut.secnote.data.remote

import app.vut.secnote.authservice.AuthServiceCoroutineGrpc
import app.vut.secnote.data.store.TokenStore
import com.github.marcoferrer.krotoplus.coroutines.withCoroutineContext
import io.grpc.StatusRuntimeException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject


class AuthServiceManager @Inject constructor(
    private val client: AuthServiceCoroutineGrpc.AuthServiceCoroutineStub,
    private val tokenStore: TokenStore
) {

    suspend fun signIn(email: String, password: String, key: String) = executeApiCall {
        client.withCoroutineContext().signIn {
            this.email = email
            this.password = password
            this.key = key
        }
    }

    suspend fun signUp(email: String, password: String, key: String) = executeApiCall {
        client.withCoroutineContext().signUp {
            this.email = email
            this.password = password
            this.key = key
        }
    }

    suspend fun signOut() = executeApiCall {
        client.withCoroutineContext().signOut {}
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
}