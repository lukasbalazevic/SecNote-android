package app.vut.secnote.domain.notes

import app.vut.secnote.data.remote.PermissionServiceManager
import app.vut.secnote.data.store.NoteStore
import app.vut.secnote.tools.extensions.convertToRoomNote
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class SyncNotesInteractor @Inject constructor(
    private val permissionServiceManager: PermissionServiceManager,
    private val noteStore: NoteStore
) : BaseCoroutineInteractor<Unit>() {
    override suspend fun build() {
        val notes = permissionServiceManager.getNotes().notesList.map { it.convertToRoomNote() }
        noteStore.syncNotes(notes)
    }
}