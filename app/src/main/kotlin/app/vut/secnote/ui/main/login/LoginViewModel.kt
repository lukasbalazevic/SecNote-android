package app.vut.secnote.ui.main.login

import android.content.res.Resources
import app.vut.secnote.R
import app.vut.secnote.domain.login.SignInInteractor
import app.vut.secnote.domain.login.SignUpInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    override val viewState: LoginViewState,
    private val signInInteractor: SignInInteractor,
    private val signUpInteractor: SignUpInteractor,
    private val resources: Resources
) : BaseCrViewModel<LoginViewState>() {

    fun signIn() = validateInput {
        signInInteractor.init(
            viewState.email.value,
            viewState.password.value
        ).execute(
            onSuccess = {
                sendEvent(NavigateToNotesEvent)
            },
            onError = {
                Timber.d(it)
                sendEvent(ShowErrorEvent(
                    title = "",
                    body = ""
                ))
            }
        )
    }

    fun signUp() = validateInput {
        signUpInteractor.init(
            viewState.email.value,
            viewState.password.value
        ).execute(
            onSuccess = {
                sendEvent(NavigateToNotesEvent)
            },
            onError = {
                Timber.d(it)
                sendEvent(ShowErrorEvent(
                    title = "",
                    body = ""
                ))
            }
        )
    }

    private fun validateInput(action: () -> Unit) {
        val isEmailValid = viewState.email.value.isNotBlank()
        val isPasswordValid = viewState.password.value.isNotBlank()

        viewState.emailError.value = if (isEmailValid.not()) resources.getString(R.string.general_email_error) else ""
        viewState.passwordError.value = if (isPasswordValid.not()) resources.getString(R.string.general_password_error) else ""

        if (isEmailValid && isPasswordValid) {
            action()
        }
    }

}

