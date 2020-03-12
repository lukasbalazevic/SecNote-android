package app.vut.secnote.ui.navigation

import app.vut.secnote.ui.navigation.NavigationViewState
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class NavigationViewModel @Inject constructor(
    override val viewState: NavigationViewState
) : BaseCrViewModel<NavigationViewState>()
