package app.vut.secnote.domain.notes

import app.vut.secnote.data.model.room.Note
import app.vut.secnote.data.store.NoteStore
import com.thefuntasty.mvvm.crinteractors.BaseFlowInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteInteractor @Inject constructor(
    private val noteStore: NoteStore
) : BaseFlowInteractor<Note>() {

    private lateinit var id: String

    fun init(id: String) = apply {
        this.id = id
    }

    override suspend fun build(): Flow<Note> = noteStore.getNote(id)
}