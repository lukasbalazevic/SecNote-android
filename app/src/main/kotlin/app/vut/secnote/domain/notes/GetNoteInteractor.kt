package app.vut.secnote.domain.notes

import app.vut.secnote.data.model.room.Note
import app.vut.secnote.data.store.NoteStore
import app.vut.secnote.domain.security.CryptoHelper
import com.thefuntasty.mvvm.crinteractors.BaseFlowInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNoteInteractor @Inject constructor(
    private val noteStore: NoteStore,
    private val cryptoHelper: CryptoHelper
) : BaseFlowInteractor<Note>() {

    private lateinit var id: String

    fun init(id: String) = apply {
        this.id = id
    }

    override suspend fun build(): Flow<Note> = noteStore.getNote(id).map {
        if (it.encrypted) {
            val decryptedBody = cryptoHelper.decryptData(it.alias, it.body)
            it.copy(body = decryptedBody)
        } else {
            it
        }
    }
}
