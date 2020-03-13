package app.vut.secnote.ui.splash

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    override val viewState: SplashViewState
) : BaseCrViewModel<SplashViewState>()
