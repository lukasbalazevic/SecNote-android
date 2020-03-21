package app.vut.secnote.ui.navigation

import app.vut.secnote.data.ui.StartDestination
import app.vut.secnote.domain.login.IsUserSignInInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class NavigationViewModel @Inject constructor(
    override val viewState: NavigationViewState,
    private val isUserSignInInteractor: IsUserSignInInteractor
) : BaseCrViewModel<NavigationViewState>() {
    override fun onStart() {
        isUserSignInInteractor.execute(
            onSuccess = {
                val startDestination = if (it) StartDestination.NOTES else StartDestination.LOGIN
                sendEvent(NavigationStartDestinationEvent(startDestination))
            }
        )
    }
}
