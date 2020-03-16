package app.vut.secnote.domain.notes

import app.vut.secnote.data.remote.PermissionServiceManager
import app.vut.secnote.noteservice.NoteResponse
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class GetNotesInteractor @Inject constructor(
    private val permissionServiceManager: PermissionServiceManager
) : BaseCoroutineInteractor<NoteResponse>() {
    override suspend fun build(): NoteResponse =
        permissionServiceManager.getNotes()
}