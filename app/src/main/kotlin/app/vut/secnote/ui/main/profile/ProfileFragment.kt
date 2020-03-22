package app.vut.secnote.ui.main.profile

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentProfileBinding
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.ui.base.BaseBindingFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class ProfileFragment : BaseBindingFragment<ProfileViewModel, ProfileViewState, FragmentProfileBinding>(), ProfileView {

    @Inject override lateinit var viewModelFactory: ProfileViewModelFactory

    override val layoutResId = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(NavigateBackEvent::class) {
            navigateBack()
        }

        observeEvent(NavigateToLoginEvent::class) {
            navigateTo(
                ProfileFragmentDirections.navigateToLoginFragment()
            )
        }

        observeEvent(ErrorOccurredEvent::class) {
            Snackbar.make(
                binding.root,
                "${resources.getString(R.string.general_error_occurred)}. ${resources.getString(R.string.general_try_again_later)}.",
                Snackbar.LENGTH_SHORT
            )
                .setAnchorView(binding.profileSignOut)
                .show()
        }
    }
}
