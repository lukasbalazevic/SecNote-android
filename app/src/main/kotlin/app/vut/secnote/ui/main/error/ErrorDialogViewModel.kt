package app.vut.secnote.ui.main.error

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class ErrorDialogViewModel @Inject constructor(
    override val viewState: ErrorDialogViewState
) : BaseCrViewModel<ErrorDialogViewState>() {
    fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }
}

