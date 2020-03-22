package app.vut.secnote.domain.notes

import app.vut.secnote.data.remote.PermissionServiceManager
import app.vut.secnote.noteservice.Note
import app.vut.secnote.noteservice.NoteResponse
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class CreateOrUpdateNoteInteractor @Inject constructor(
    private val permissionServiceManager: PermissionServiceManager
) : BaseCoroutineInteractor<NoteResponse>() {

    private var prototype: Note? = null
    private var id: String? = null
    private var title: String? = null
    private var body: String? = null
    private var categories: List<String>? = null
    private var encrypted: Boolean? = null
    private var alias: String? = null

    fun init(
        prototype: Note? = null,
        id: String? = null,
        title: String? = null,
        body: String? = null,
        categories: List<String>? = null,
        encrypted: Boolean? = null,
        alias: String? = null
    ) = apply {
        this.prototype = prototype
        this.id = id
        this.title = title
        this.body = body
        this.categories = categories
        this.encrypted = encrypted
        this.alias = alias
    }

    override suspend fun build(): NoteResponse =
        permissionServiceManager.createOrUpdateNote(prototype, id, title, body, categories, encrypted, alias)
}