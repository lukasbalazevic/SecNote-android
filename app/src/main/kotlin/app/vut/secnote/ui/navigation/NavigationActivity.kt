package app.vut.secnote.ui.navigation

import app.vut.secnote.R
import app.vut.secnote.databinding.ActivityNavigationBinding
import app.vut.secnote.ui.base.BaseBindingActivity
import javax.inject.Inject

class NavigationActivity :
    BaseBindingActivity<NavigationViewModel, NavigationViewState, ActivityNavigationBinding>(),
    NavigationView {

    @Inject
    override lateinit var viewModelFactory: NavigationViewModelFactory

    override val layoutResId = R.layout.activity_navigation
}
