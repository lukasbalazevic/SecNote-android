package app.vut.secnote.data.remote

import app.vut.secnote.authservice.AuthServiceCoroutineGrpc
import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import com.github.marcoferrer.krotoplus.coroutines.withCoroutineContext
import io.grpc.CallOptions
import io.grpc.Metadata
import io.grpc.stub.MetadataUtils
import javax.inject.Inject


class AuthServiceManager @Inject constructor(
    private val client: AuthServiceCoroutineGrpc.AuthServiceCoroutineStub,
    cryptoHelper: CryptoHelper,
    tokenStore: TokenStore
) : ServiceManager(cryptoHelper, tokenStore) {

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


}