package app.vut.secnote.ui.main.login

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class LoginViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<LoginViewModel>
) : BaseViewModelFactory<LoginViewModel>() {
    override val viewModelClass: KClass<LoginViewModel> = LoginViewModel::class
}
