package app.vut.secnote.ui.main.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import app.vut.secnote.domain.notes.GetNotesInteractor
import app.vut.secnote.domain.notes.SyncNotesInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    override val viewState: NotesViewState,
    private val syncNotesInteractor: SyncNotesInteractor,
    private val getNotesInteractor: GetNotesInteractor
) : BaseCrViewModel<NotesViewState>() {

    override fun onStart() {
        getNotesInteractor.execute(
            onNext = {
                viewState.list.value = it
            }
        )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getNotes() {
        syncNotesInteractor.execute(
            onSuccess = {
                Timber.d("Sync complete")
            },
            onError = {
                Timber.d(it)
            }
        )
    }

    fun createNote() {
        sendEvent(NavigateToCreateOrUpdateNoteEvent())
    }
}

