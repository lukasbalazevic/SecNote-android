package app.vut.secnote.ui.main.login

import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class LoginViewState @Inject constructor() : ViewState {
    val email = DefaultValueLiveData("")
    val password = DefaultValueLiveData("")

    val emailError = DefaultValueLiveData("")
    val passwordError = DefaultValueLiveData("")
}
