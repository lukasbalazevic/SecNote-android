package app.vut.secnote.data.remote

import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import app.vut.secnote.noteservice.DeleteRequest
import app.vut.secnote.noteservice.Note
import app.vut.secnote.noteservice.NoteOperationRequest
import app.vut.secnote.permissionservice.PermissionServiceGrpcKt
import app.vut.secnote.permissionservice.Request
import javax.inject.Inject


class PermissionServiceManager @Inject constructor(
    private val client: PermissionServiceGrpcKt.PermissionServiceCoroutineStub,
    cryptoHelper: CryptoHelper,
    tokenStore: TokenStore,
    authServiceManager: AuthServiceManager
) : ServiceManager(cryptoHelper, tokenStore, authServiceManager) {

    suspend fun getNotes() = executeApiCall {

        val request = Request.newBuilder().build()
        val digest = cryptoHelper.hashMessage(request.toByteArray())
        client
            .executeWithMetadata(digest)
            .getNotes(request)
    }

    suspend fun deleteNote(id: String) = executeApiCall {
        val request = DeleteRequest.newBuilder().setId(id).build()
        val digest = cryptoHelper.hashMessage(request.toByteArray())
        client
            .executeWithMetadata(digest)
            .deleteNote(request)
    }

    suspend fun createOrUpdateNote(
        prototype: Note? = null,
        id: String? = null,
        title: String? = null,
        body: String? = null,
        categories: List<String>? = null,
        encrypted: Boolean? = null,
        alias: String? = null) = executeApiCall {
        val data = (if (prototype != null) Note.newBuilder(prototype) else Note.newBuilder()).apply {
            id?.also { setId(it) }
            title?.also { setTitle(it) }
            body?.also { setBody(it) }
            categories?.also { addAllCategories(it) }
            encrypted?.also { setEncrypted(it) }
            alias?.also { setAlias(it) }
        }.build()

        val request = NoteOperationRequest.newBuilder().setNote(data).build()
        val digest = cryptoHelper.hashMessage(request.toByteArray())
        client
            .executeWithMetadata(digest)
            .addOrUpdateNote(request)
    }
}
