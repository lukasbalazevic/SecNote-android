package app.vut.secnote.ui.main.notes

import android.content.res.Resources
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import app.vut.secnote.domain.notes.GetNotesInteractor
import app.vut.secnote.domain.notes.SyncNotesInteractor
import app.vut.secnote.tools.extensions.checkForUserNoteAuthenticatedException
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    override val viewState: NotesViewState,
    private val syncNotesInteractor: SyncNotesInteractor,
    private val getNotesInteractor: GetNotesInteractor,
    private val resources: Resources
) : BaseCrViewModel<NotesViewState>() {

    override fun onStart() {
        getNotesInteractor.execute(
            onNext = {
                viewState.list.value = it
                viewState.loading.value = false
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
                it.checkForUserNoteAuthenticatedException(
                    authorize = {
                        sendEvent(AuthorizeDeviceEvent)
                    },
                    logOutUser = {
                        sendEvent(LogOutUserEvent)
                    },
                    showError = {
                        sendEvent(
                            ShowErrorEvent(
                                message = resources.getString(it.body),
                                imageSrc = it.imageSrc
                            )
                        )
                    }
                )
            }
        )
    }

    fun createNote() {
        sendEvent(NavigateToCreateOrUpdateNoteEvent())
    }
}

