package app.vut.secnote.ui.main.profile

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentProfileBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class ProfileFragment : BaseBindingFragment<ProfileViewModel, ProfileViewState, FragmentProfileBinding>(), ProfileView {


    @Inject override lateinit var viewModelFactory: ProfileViewModelFactory

    override val layoutResId = R.layout.fragment_profile
}
