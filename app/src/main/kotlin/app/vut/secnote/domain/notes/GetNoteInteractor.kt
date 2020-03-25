package app.vut.secnote.domain.notes

import app.vut.secnote.data.model.room.Note
import app.vut.secnote.data.store.NoteStore
import app.vut.secnote.domain.security.CryptoHelper
import com.thefuntasty.mvvm.crinteractors.BaseFlowInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception
import javax.inject.Inject

class GetNoteInteractor @Inject constructor(
    private val noteStore: NoteStore,
    private val cryptoHelper: CryptoHelper
) : BaseFlowInteractor<Note>() {

    private lateinit var id: String

    fun init(id: String) = apply {
        this.id = id
    }

    override fun build(): Flow<Note> = noteStore.getNote(id).flatMapConcat {
        if (it.encrypted) {
            try {
                val decryptedBody = cryptoHelper.decryptData(it.alias, it.body)
                flowOf(it.copy(body = decryptedBody))
            } catch (e: IllegalStateException) {
                flow {
                    emit(it)
                    throw e
                }
            } catch (e: Exception) {
                // Case when server send back note that is encrypted but is not.
                // Should not happen on new version
                flow {
                    emit(it)
                    throw e
                }
            }
        } else {
            flowOf(it)
        }
    }
}
