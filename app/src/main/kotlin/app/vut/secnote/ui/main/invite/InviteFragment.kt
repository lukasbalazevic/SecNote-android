package app.vut.secnote.ui.main.invite

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentInviteBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class InviteFragment : BaseBindingFragment<InviteViewModel, InviteViewState, FragmentInviteBinding>(), InviteView {

    @Inject override lateinit var viewModelFactory: InviteViewModelFactory

    override val layoutResId = R.layout.fragment_invite
}
