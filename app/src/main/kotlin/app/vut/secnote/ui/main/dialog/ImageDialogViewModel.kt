package app.vut.secnote.ui.main.dialog

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class ImageDialogViewModel @Inject constructor(
    override val viewState: ImageDialogViewState
) : BaseCrViewModel<ImageDialogViewState>() {
    fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }
}

