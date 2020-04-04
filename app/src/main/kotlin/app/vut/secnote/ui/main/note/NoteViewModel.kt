package app.vut.secnote.ui.main.note

import android.content.res.Resources
import app.vut.secnote.R
import app.vut.secnote.domain.login.SignOutInteractor
import app.vut.secnote.domain.notes.CreateOrUpdateNoteInteractor
import app.vut.secnote.domain.notes.DeleteNoteInteractor
import app.vut.secnote.domain.notes.GetNoteInteractor
import app.vut.secnote.domain.tutorial.GetDecryptionTutorialInteractor
import app.vut.secnote.domain.tutorial.MarkDecryptionTutorialInteractor
import app.vut.secnote.tools.extensions.checkForUserNoteAuthenticatedException
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    override val viewState: NoteViewState,
    private val arguments: NoteFragmentArgs,
    private val resources: Resources,
    private val getNoteInteractor: GetNoteInteractor,
    private val createOrUpdateNoteInteractor: CreateOrUpdateNoteInteractor,
    private val deleteNoteInteractor: DeleteNoteInteractor,
    private val signOutInteractor: SignOutInteractor,
    private val getDecryptionTutorialInteractor: GetDecryptionTutorialInteractor,
    private val markDecryptionTutorialInteractor: MarkDecryptionTutorialInteractor
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
                    checkDecryptionTutorial(it.encrypted)
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
        deleteNoteInteractor.init(viewState.id.value).execute(
            onSuccess = {
                sendEvent(NavigateBackEvent)
            },
            onError = {
                it.checkForUserNoteAuthenticatedException(
                    authorize = {
                        sendEvent(AuthorizeDeviceEvent)
                    },
                    logOutUser = {
                        signOut()
                    },
                    showError = {
                        sendEvent(
                            ErrorOccurredEvent(
                                resources.getString(R.string.general_error_occurred),
                                resources.getString(R.string.general_try_again_later)
                            )
                        )
                    }
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
                it.checkForUserNoteAuthenticatedException(
                    authorize = {
                        sendEvent(AuthorizeDeviceEvent)
                    },
                    logOutUser = {
                        signOut()
                    },
                    showError = {
                        sendEvent(
                            ErrorOccurredEvent(
                                resources.getString(R.string.general_error_occurred),
                                resources.getString(R.string.general_try_again_later)
                            )
                        )
                    }
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

    fun markDecryptionTutorial() {
        markDecryptionTutorialInteractor.execute {}
    }

    private fun checkDecryptionTutorial(encrypted: Boolean) {
        if (encrypted.not()) return
        getDecryptionTutorialInteractor.execute(
            onSuccess = {
                if (it.not()) sendEvent(ShowDecryptionTutorialEvent)
            }
        )
    }

    private fun signOut() {
        signOutInteractor.init(includeServerCall = false).execute(
            onSuccess = {
                sendEvent(LogOutUserEvent)
            }
        )
    }
}
