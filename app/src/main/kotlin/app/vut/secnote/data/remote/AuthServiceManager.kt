package app.vut.secnote.data.remote

import app.vut.secnote.authservice.AuthServiceCoroutineGrpc
import app.vut.secnote.domain.security.CryptoHelper
import com.github.marcoferrer.krotoplus.coroutines.withCoroutineContext
import io.grpc.CallOptions
import io.grpc.Metadata
import io.grpc.stub.MetadataUtils
import javax.inject.Inject


class AuthServiceManager @Inject constructor(
    private val client: AuthServiceCoroutineGrpc.AuthServiceCoroutineStub,
    private val cryptoHelper: CryptoHelper
) : ServiceManager() {

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

    suspend fun executeWithMetadata(token: String, hash: String) = executeApiCall {
        val message = "Request in string"
        val signature = cryptoHelper.signAndEncodeDataBase64(message.toByteArray())
        val encodedMessage = cryptoHelper.encodeBase64(message.toByteArray())

        val header = Metadata()
        header.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), token)
        header.put(Metadata.Key.of("Digest", Metadata.ASCII_STRING_MARSHALLER), encodedMessage)
        header.put(Metadata.Key.of("Signature", Metadata.ASCII_STRING_MARSHALLER), signature)

        MetadataUtils.attachHeaders(
            client.withCoroutineContext(),
            header
        ).verify()
    }
}