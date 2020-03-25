package app.vut.secnote.data.remote

import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import app.vut.secnote.noteservice.Note
import app.vut.secnote.permissionservice.PermissionServiceCoroutineGrpc
import com.github.marcoferrer.krotoplus.coroutines.withCoroutineContext
import javax.inject.Inject


class PermissionServiceManager @Inject constructor(
    private val client: PermissionServiceCoroutineGrpc.PermissionServiceCoroutineStub,
    cryptoHelper: CryptoHelper,
    tokenStore: TokenStore,
    authServiceManager: AuthServiceManager
) : ServiceManager(cryptoHelper, tokenStore, authServiceManager) {

    suspend fun getNotes() = executeApiCall {
        client
            .executeWithMetadata("")
            .withCoroutineContext()
            .getNotes {}
    }

    suspend fun deleteNote(id: String) = executeApiCall {
        client
            .executeWithMetadata("")
            .withCoroutineContext()
            .deleteNote {
                this.id = id
            }
    }

    suspend fun createOrUpdateNote(
        prototype: Note? = null,
        id: String? = null,
        title: String? = null,
        body: String? = null,
        categories: List<String>? = null,
        encrypted: Boolean? = null,
        alias: String? = null) = executeApiCall {
        client
            .executeWithMetadata("")
            .withCoroutineContext()
            .addOrUpdateNote {
                note = (if (prototype != null) Note.newBuilder(prototype) else Note.newBuilder()).apply {
                    id?.also { setId(it) }
                    title?.also { setTitle(it) }
                    body?.also { setBody(it) }
                    categories?.also { addAllCategories(it) }
                    encrypted?.also { setEncrypted(it) }
                    alias?.also { setAlias(it) }
                }.build()
            }
    }
}
