package app.vut.secnote.data.remote

import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import io.grpc.Metadata
import io.grpc.StatusRuntimeException
import io.grpc.stub.AbstractStub
import io.grpc.stub.MetadataUtils
import timber.log.Timber
import java.net.UnknownHostException

abstract class ServiceManager(val cryptoHelper: CryptoHelper, private val tokenStore: TokenStore) {

    suspend fun <T : AbstractStub<T>> T.executeWithMetadata(request: String) : T = executeApiCall {

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

    suspend fun <T> executeApiCall(apiCall: suspend () -> T): T {
        try {
            return apiCall()
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