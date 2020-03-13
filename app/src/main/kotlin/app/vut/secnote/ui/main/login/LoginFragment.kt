package app.vut.secnote.ui.main.login

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentLoginBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class LoginFragment : BaseBindingFragment<LoginViewModel, LoginViewState, FragmentLoginBinding>(), LoginView {

    @Inject override lateinit var viewModelFactory: LoginViewModelFactory

    override val layoutResId = R.layout.fragment_login
}
