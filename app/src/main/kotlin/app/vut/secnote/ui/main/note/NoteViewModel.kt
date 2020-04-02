package app.vut.secnote.ui.main.note

import android.content.res.Resources
import app.vut.secnote.R
import app.vut.secnote.domain.notes.CreateOrUpdateNoteInteractor
import app.vut.secnote.domain.notes.DeleteNoteInteractor
import app.vut.secnote.domain.notes.GetNoteInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    override val viewState: NoteViewState,
    private val arguments: NoteFragmentArgs,
    private val resources: Resources,
    private val getNoteInteractor: GetNoteInteractor,
    private val createOrUpdateNoteInteractor: CreateOrUpdateNoteInteractor,
    private val deleteNoteInteractor: DeleteNoteInteractor
) : BaseCrViewModel<NoteViewState>() {

    override fun onStart() {
        arguments.noteId?.also { note ->
            getNoteInteractor.init(note).execute(
                onNext = {
                    viewState.note.value = it
                    viewState.id.value = it.id
                    viewState.title.value = it.title
                    viewState.body.value = it.body
                    viewState.alias.value = it.alias
                    viewState.categories.value = it.categoryList
                },
                onError = {
                    when (it) {
                        is IllegalStateException -> {
                            sendEvent(NoDecryptionKeyEvent)
                        }
                        else -> Timber.e(it)
                    }
                }
            )
        }
    }

    fun deleteNote() {
        if (viewState.id.value.isBlank()) {
            sendEvent(
                ErrorOccurredEvent(
                    resources.getString(R.string.general_error_occurred),
                    resources.getString(R.string.general_try_again_later)
                )
            )
            return
        }
        deleteNoteInteractor.init(viewState.id.value ?: "").execute(
            onSuccess = {
                sendEvent(NavigateBackEvent)
            },
            onError = {
                ErrorOccurredEvent(
                    resources.getString(R.string.general_error_occurred),
                    resources.getString(R.string.general_try_again_later)
                )
            }
        )
    }

    fun saveNote() {
        createOrUpdateNoteInteractor.init(
            id = if (viewState.id.value.isBlank()) null else viewState.id.value,
            title = viewState.title.value,
            body = viewState.body.value,
            categories = viewState.categories.value,
            encrypted = viewState.alias.value.isNotBlank(),
            alias = viewState.alias.value
        ).execute(
            onSuccess = {
                if (arguments.noteId == null) {
                    sendEvent(NavigateBackEvent)
                } else {
                    sendEvent(NoteSavedEvent)
                }
            },
            onError = {
                Timber.d(it)
                sendEvent(
                    ErrorOccurredEvent(
                        resources.getString(R.string.general_error_occurred),
                        resources.getString(R.string.general_try_again_later)
                    )
                )
            }
        )
    }

    fun navigateBack() {
        if (viewState.changed.value == true) {
            sendEvent(BackPressConfirmEvent)
        } else {
            sendEvent(NavigateBackEvent)
        }
    }
}
