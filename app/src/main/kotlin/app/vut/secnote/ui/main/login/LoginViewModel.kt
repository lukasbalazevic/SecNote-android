package app.vut.secnote.ui.main.login

import app.vut.secnote.domain.login.SignInInteractor
import app.vut.secnote.domain.login.SignUpInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    override val viewState: LoginViewState,
    private val signInInteractor: SignInInteractor,
    private val signUpInteractor: SignUpInteractor
) : BaseCrViewModel<LoginViewState>() {

    fun signIn() {
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

    fun signUp() {
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


}

