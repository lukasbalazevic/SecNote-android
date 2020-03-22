package app.vut.secnote.ui.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import app.vut.secnote.R
import app.vut.secnote.data.model.ui.PinState
import app.vut.secnote.data.model.ui.StartDestination
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
        observeEvent(NavigationStartDestinationEvent::class) {
            setupNavigationFragment(it.destination)
        }
    }

    private fun setupNavigationFragment(destination: StartDestination) {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)?.navController?.apply {
            val graph = navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = when (destination) {
                    StartDestination.LOGIN -> R.id.fragment_login
                    StartDestination.PIN_SET,
                    StartDestination.PIN_AUTH -> R.id.fragment_pin
                }
            }

            val extras = when (destination) {
                StartDestination.LOGIN -> intent.extras
                StartDestination.PIN_SET -> Bundle().apply { putSerializable("pin_state", PinState.PIN_SET) }
                StartDestination.PIN_AUTH -> Bundle().apply { putSerializable("pin_state", PinState.AUTHORISE) }
            }

            setGraph(graph, extras)
        } ?: error("Unexpected state")
    }
}


