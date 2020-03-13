package app.vut.secnote.ui.main.pin

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class PinViewModel @Inject constructor(
    override val viewState: PinViewState
) : BaseCrViewModel<PinViewState>()

