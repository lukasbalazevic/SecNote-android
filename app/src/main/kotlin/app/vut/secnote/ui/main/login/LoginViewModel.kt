package app.vut.secnote.ui.main.login

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    override val viewState: LoginViewState
) : BaseCrViewModel<LoginViewState>() {

    fun callGrpc() {

    }
}

