package app.vut.secnote.domain.notes

import app.vut.secnote.data.remote.PermissionServiceManager
import app.vut.secnote.noteservice.NoteResponse
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class DeleteNoteInteractor @Inject constructor(
    private val permissionServiceManager: PermissionServiceManager
) : BaseCoroutineInteractor<NoteResponse>() {

    private lateinit var id: String

    fun init(id: String) = apply {
        this.id = id
    }

    override suspend fun build() = permissionServiceManager.deleteNote(id)

}