package app.vut.secnote.data.remote

import io.grpc.StatusRuntimeException
import timber.log.Timber
import java.net.UnknownHostException

abstract class ServiceManager {

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