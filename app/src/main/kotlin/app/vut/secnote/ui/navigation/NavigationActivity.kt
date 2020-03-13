package app.vut.secnote.ui.navigation

import android.content.Context
import android.content.Intent
import app.vut.secnote.R
import app.vut.secnote.databinding.ActivityNavigationBinding
import app.vut.secnote.ui.base.BaseBindingActivity
import javax.inject.Inject

class NavigationActivity :
    BaseBindingActivity<NavigationViewModel, NavigationViewState, ActivityNavigationBinding>(),
    NavigationView {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, NavigationActivity::class.java)
    }

    @Inject
    override lateinit var viewModelFactory: NavigationViewModelFactory

    override val layoutResId = R.layout.activity_navigation
}
