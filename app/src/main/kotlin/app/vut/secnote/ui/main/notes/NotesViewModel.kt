package app.vut.secnote.ui.main.notes

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import app.vut.secnote.domain.notes.GetNotesInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    override val viewState: NotesViewState,
    private val getNotesInteractor: GetNotesInteractor
) : BaseCrViewModel<NotesViewState>() {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getNotes() {
        getNotesInteractor.execute(
            onSuccess = {
                //viewState.list.value = it.notesList
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

