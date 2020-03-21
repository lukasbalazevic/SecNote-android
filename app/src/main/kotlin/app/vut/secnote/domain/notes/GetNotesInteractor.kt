package app.vut.secnote.domain.notes

import app.vut.secnote.data.model.room.Note
import app.vut.secnote.data.store.NoteStore
import com.thefuntasty.mvvm.crinteractors.BaseFlowInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotesInteractor @Inject constructor(
    private val noteStore: NoteStore
) : BaseFlowInteractor<List<Note>>() {
    override suspend fun build(): Flow<List<Note>> = noteStore.getNotes()
}