package app.vut.secnote.ui.main.login

import android.app.KeyguardManager
import android.content.res.Resources
import android.util.Patterns
import app.vut.secnote.R
import app.vut.secnote.data.model.error.AppError
import app.vut.secnote.data.model.ui.PinState
import app.vut.secnote.domain.login.SignInInteractor
import app.vut.secnote.domain.login.SignUpInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    override val viewState: LoginViewState,
    private val signInInteractor: SignInInteractor,
    private val signUpInteractor: SignUpInteractor,
    private val keyguardManager: KeyguardManager,
    private val resources: Resources
) : BaseCrViewModel<LoginViewState>() {

    fun signIn() = validateInput {
        checkIfDeviceIsSecure {
            signInInteractor.init(
                viewState.email.value,
                viewState.password.value
            ).execute(
                onSuccess = {
                    sendEvent(NavigateToPinWithPopEvent(PinState.AUTHORISE))
                },
                onError = {
                    sendErrorEvent(it)
                }
            )
        }
    }

    fun signUp() = validateInput {
        checkIfDeviceIsSecure {
            signUpInteractor.init(
                viewState.email.value,
                viewState.password.value
            ).execute(
                onSuccess = {
                    sendEvent(NavigateToPinWithPopEvent(PinState.AUTHORISE))
                },
                onError = {
                    sendErrorEvent(it)
                }
            )
        }
    }

    private fun sendErrorEvent(e: Throwable) {
        if (e is AppError) {
            sendEvent(ShowErrorEvent(
                message = resources.getString(e.body),
                imageSrc = e.imageSrc
            ))
            return
        }
        Timber.d(e)
        error("Invalid error type")
    }


    private fun checkIfDeviceIsSecure(action: () -> Unit) {
        if (!keyguardManager.isDeviceSecure) {
            sendEvent(NavigateToPinEvent(PinState.PIN_SET))
        } else {
            action()
        }
    }

    private fun validateInput(action: () -> Unit) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(viewState.email.value).matches()
        val isPasswordValid = viewState.password.value.isNotBlank()

        viewState.emailError.value = if (isEmailValid.not()) resources.getString(R.string.general_email_error) else ""
        viewState.passwordError.value = if (isPasswordValid.not()) resources.getString(R.string.general_password_error) else ""

        if (isEmailValid && isPasswordValid) {
            action()
        }
    }
}
