package app.vut.secnote.ui.navigation

import android.app.KeyguardManager
import android.hardware.fingerprint.FingerprintManager
import app.vut.secnote.data.model.ui.StartDestination
import app.vut.secnote.domain.login.IsUserSignInInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import java.security.KeyStore
import java.util.concurrent.Executors
import javax.inject.Inject

class NavigationViewModel @Inject constructor(
    override val viewState: NavigationViewState,
    private val isUserSignInInteractor: IsUserSignInInteractor,
    private val keyguard: KeyguardManager
) : BaseCrViewModel<NavigationViewState>() {
    override fun onStart() {
        isUserSignInInteractor.execute(
            onSuccess = {
               if (!it) {
                   sendEvent(NavigationStartDestinationEvent(StartDestination.LOGIN))
               } else {
                   checkIfPinIsSetUp()
               }
            }
        )
    }

    private fun checkIfPinIsSetUp() {
        if (keyguard.isDeviceSecure) {
            sendEvent(NavigationStartDestinationEvent(StartDestination.PIN_AUTH))
        } else {
            sendEvent(NavigationStartDestinationEvent(StartDestination.PIN_SET))
        }
    }
}
