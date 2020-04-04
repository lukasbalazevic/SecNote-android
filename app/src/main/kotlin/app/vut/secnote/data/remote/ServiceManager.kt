package app.vut.secnote.data.remote

import android.security.keystore.UserNotAuthenticatedException
import app.vut.secnote.data.model.error.InvalidCredentialsError
import app.vut.secnote.data.model.error.NoConnectionError
import app.vut.secnote.data.model.error.UnknownAppError
import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import io.grpc.Metadata
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.AbstractStub
import io.grpc.stub.MetadataUtils
import kotlinx.coroutines.CancellationException
import timber.log.Timber
import java.net.UnknownHostException

abstract class ServiceManager(
    private val cryptoHelper: CryptoHelper,
    private val tokenStore: TokenStore,
    private val authServiceManager: AuthServiceManager
) {

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

    suspend fun <T> executeApiCall(apiCall: suspend () -> T): T {
        return try {
            apiCall()
        } catch (e: StatusRuntimeException) {
            checkStatusRuntimeException(e, apiCall)
        } catch (e: KotlinNullPointerException) {
            throw e
        } catch (e: UnknownHostException) {
            throw NoConnectionError(e)
        } catch (e: UserNotAuthenticatedException) {
            throw e
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Timber.e(e)
            throw UnknownAppError(e)
        }
    }

    private suspend fun <T> checkStatusRuntimeException(e: StatusRuntimeException, apiCall: suspend () -> T): T =
        when (e.status.code) {
            Status.Code.OK,
            Status.Code.CANCELLED,
            Status.Code.UNKNOWN,
            Status.Code.INVALID_ARGUMENT,
            Status.Code.DEADLINE_EXCEEDED,
            Status.Code.NOT_FOUND,
            Status.Code.ALREADY_EXISTS,
            Status.Code.PERMISSION_DENIED,
            Status.Code.RESOURCE_EXHAUSTED,
            Status.Code.FAILED_PRECONDITION,
            Status.Code.ABORTED,
            Status.Code.OUT_OF_RANGE,
            Status.Code.UNIMPLEMENTED,
            Status.Code.INTERNAL,
            Status.Code.UNAVAILABLE,
            Status.Code.DATA_LOSS -> throw UnknownAppError(e)
            Status.Code.UNAUTHENTICATED -> handleUnauthenticatedError(e, apiCall)
        }

    private suspend fun <T> handleUnauthenticatedError(e: StatusRuntimeException, apiCall: suspend () -> T): T {
        val successful = authServiceManager.renewToken()
        if (successful) {
            return apiCall()
        }
        throw InvalidCredentialsError(e)
    }
}
