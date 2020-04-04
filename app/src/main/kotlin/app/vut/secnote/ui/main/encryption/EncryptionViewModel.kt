package app.vut.secnote.ui.main.encryption

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import app.vut.secnote.domain.security.GetKeyAliasesInteractor
import app.vut.secnote.domain.tutorial.GetEncryptionTutorialInteractor
import app.vut.secnote.domain.tutorial.MarkEncryptionTutorialInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class EncryptionViewModel @Inject constructor(
    override val viewState: EncryptionViewState,
    private val getKeyAliasesInteractor: GetKeyAliasesInteractor,
    private val getEncryptionTutorialInteractor: GetEncryptionTutorialInteractor,
    private val markEncryptionTutorialInteractor: MarkEncryptionTutorialInteractor
) : BaseCrViewModel<EncryptionViewState>() {

    override fun onStart() {
        getEncryptionTutorialInteractor.execute(
            onSuccess = {
                if (!it) sendEvent(ShowEncryptionTutorialEvent)
            }
        )
    }

    fun markTutorialAsSeen() {
        markEncryptionTutorialInteractor.execute { }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getKeyAliases() {
        getKeyAliasesInteractor.execute(
            onSuccess = {
                viewState.aliases.value = it
            }
        )
    }

    fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }

    fun onKeySelection(alias: String) {
        viewState.selected.value = if (viewState.selected.value == alias) {
            ""
        } else {
            alias
        }
        sendEvent(KeySelectionEvent(viewState.selected.value))
    }

    fun createKey() {
        sendEvent(CreateKeyEvent)
    }
}

