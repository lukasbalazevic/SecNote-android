package app.vut.secnote.ui.main.note

import app.vut.secnote.domain.notes.CreateOrUpdateNoteInteractor
import app.vut.secnote.domain.notes.GetNoteInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    override val viewState: NoteViewState,
    private val arguments: NoteFragmentArgs,
    private val getNoteInteractor: GetNoteInteractor,
    private val createOrUpdateNoteInteractor: CreateOrUpdateNoteInteractor
) : BaseCrViewModel<NoteViewState>() {

    override fun onStart() {
        arguments.noteId?.also { note ->
            getNoteInteractor.init(note).execute(
                onNext = {
                    viewState.id.value = it.id
                    viewState.title.value = it.title
                    viewState.body.value = it.body
                    viewState.categories.value = it.categoryList
                }
            )
        }
    }

    fun saveNote() {
        createOrUpdateNoteInteractor.init(
            id = viewState.id.value,
            title = viewState.title.value,
            body = viewState.body.value,
            categories = viewState.categories.value,
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
