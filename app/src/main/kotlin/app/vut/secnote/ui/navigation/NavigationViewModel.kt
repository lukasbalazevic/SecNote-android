package app.vut.secnote.ui.navigation

import android.app.KeyguardManager
import app.vut.secnote.data.model.ui.StartDestination
import app.vut.secnote.domain.login.IsUserSignInInteractor
import app.vut.secnote.domain.login.SignOutInteractor
import app.vut.secnote.domain.security.ValidateDeviceKeyInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class NavigationViewModel @Inject constructor(
    override val viewState: NavigationViewState,
    private val isUserSignInInteractor: IsUserSignInInteractor,
    private val keyguard: KeyguardManager,
    private val signOutInteractor: SignOutInteractor,
    private val validateDeviceKeyInteractor: ValidateDeviceKeyInteractor
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
            validateKey()
        } else {
            securityChangeLogOutUser()
        }
    }

    private fun validateKey() {
        validateDeviceKeyInteractor.execute(
            onSuccess = {
                if (it) {
                    sendEvent(NavigationStartDestinationEvent(StartDestination.PIN_AUTH))
                } else {
                    securityChangeLogOutUser()
                }
            },
            onError = {
                securityChangeLogOutUser()
            }
        )
    }

    private fun securityChangeLogOutUser() {
        signOutInteractor.init(false).execute(
            onSuccess = {
                sendEvent(NavigationStartDestinationEvent(StartDestination.LOGIN))
            },
            onError = {
                error("Failed preconditions")
            }
        )
    }
}
