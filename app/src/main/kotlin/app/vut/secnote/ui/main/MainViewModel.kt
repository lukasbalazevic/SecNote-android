package app.vut.secnote.ui.main

import app.vut.secnote.ui.main.MainViewState
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    override val viewState: MainViewState
) : BaseCrViewModel<MainViewState>()
