package app.vut.secnote.ui.main.pin

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.biometric.BiometricPrompt
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentPinBinding
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.tools.extensions.runOnUIThread
import app.vut.secnote.tools.security.BiometricCallbackInterface
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class PinFragment : BaseBindingFragment<PinViewModel, PinViewState, FragmentPinBinding>(), PinView, BiometricCallbackInterface {

    companion object {
        const val REQUEST_CODE_PIN_SETTINGS = 0
    }

    @Inject override lateinit var viewModelFactory: PinViewModelFactory
    @Inject lateinit var biometricPrompt: BiometricPrompt
    @Inject lateinit var promptInfo: BiometricPrompt.PromptInfo

    override val layoutResId = R.layout.fragment_pin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(AuthenticateDeviceEvent::class) {
            biometricPrompt.authenticate(promptInfo)
        }

        observeEvent(SetPinEvent::class) {
            startActivityForResult(Intent(Settings.ACTION_SECURITY_SETTINGS), REQUEST_CODE_PIN_SETTINGS)
        }

        observeEvent(NavigateBackEvent::class) {
            navigateBack()
        }

        observeEvent(NavigateToNotesEvent::class) {
            navigateTo(PinFragmentDirections.navigateToNotesFragment())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_PIN_SETTINGS -> viewModel.checkIfDeviceIsSecure()
        }
    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        // TODO show error
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) = runOnUIThread {
        viewModel.checkState()
    }

    override fun onAuthenticationFailed() {
        // TODO show error
    }
}
