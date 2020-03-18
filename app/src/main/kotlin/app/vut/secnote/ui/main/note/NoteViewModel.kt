package app.vut.secnote.ui.main.note

import app.vut.secnote.domain.notes.CreateOrUpdateNoteInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    override val viewState: NoteViewState,
    private val createOrUpdateNoteInteractor: CreateOrUpdateNoteInteractor
) : BaseCrViewModel<NoteViewState>() {

    fun saveNote() {
        createOrUpdateNoteInteractor.init(
            title = viewState.title.value,
            body = viewState.body.value,
            categories = emptyList(),
            encrypted = false,
            alias = ""
        ).execute(
            onSuccess = {
                Timber.d(it.toString())
            },
            onError = {
                Timber.d(it)
            }
        )
    }

    fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }

}

