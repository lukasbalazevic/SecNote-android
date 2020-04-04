package app.vut.secnote.ui.main.login

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentLoginBinding
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class LoginFragment : BaseBindingFragment<LoginViewModel, LoginViewState, FragmentLoginBinding>(), LoginView {

    @Inject override lateinit var viewModelFactory: LoginViewModelFactory

    override val layoutResId = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(NavigateToPinEvent::class) {
            navigateTo(
                LoginFragmentDirections.navigateToPinFragment(it.state)
            )
        }

        observeEvent(ShowErrorEvent::class) {
            navigateTo(
                LoginFragmentDirections.navigateToImageDialog(
                    it.message,
                    it.imageSrc
                )
            )
        }
    }
}
