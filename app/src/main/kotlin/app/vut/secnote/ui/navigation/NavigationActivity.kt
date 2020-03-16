package app.vut.secnote.ui.navigation

import android.content.Context
import android.content.Intent
import androidx.navigation.fragment.NavHostFragment
import app.vut.secnote.R
import app.vut.secnote.data.ui.StartDestination
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

    override fun onStart() {
        super.onStart()
        setupNavigationFragment(StartDestination.LOGIN)
    }

    private fun setupNavigationFragment(destination: StartDestination) {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)?.navController?.apply {
            val graph = navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = when (destination) {
                    StartDestination.LOGIN -> R.id.fragment_login
                    StartDestination.PIN -> R.id.fragment_pin
                    StartDestination.NOTES -> R.id.fragment_notes
                }
            }
            setGraph(graph, intent.extras)
        } ?: error("Unexpected state")
    }
}
