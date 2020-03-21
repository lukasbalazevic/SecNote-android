package app.vut.secnote.ui.main.pin

import android.app.KeyguardManager
import app.vut.secnote.data.model.ui.PinState
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class PinViewModel @Inject constructor(
    override val viewState: PinViewState,
    private val keyguardManager: KeyguardManager
) : BaseCrViewModel<PinViewState>() {

    override fun onStart() {
        if (viewState.state.value == PinState.AUTHORISE) {
            sendEvent(AuthenticateDeviceEvent)
        }
    }

    fun pinAction() {
        when (viewState.state.value) {
            PinState.PIN_SET -> sendEvent(SetPinEvent)
            PinState.AUTHORISE,
            PinState.REAUTHORISE -> sendEvent(AuthenticateDeviceEvent)
        }
    }

    fun checkState() {
        when (viewState.state.value) {
            PinState.AUTHORISE -> sendEvent(NavigateToNotesEvent)
            PinState.REAUTHORISE -> sendEvent(NavigateBackEvent)
            else -> error("Wrong state")
        }
    }

    fun checkIfDeviceIsSecure() {
        if (keyguardManager.isDeviceSecure) {
            viewState.state.value = PinState.AUTHORISE
            sendEvent(AuthenticateDeviceEvent)
        } else {
            // TODO show error
        }
    }
}

