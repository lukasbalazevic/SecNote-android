package app.vut.secnote.ui.main.encryption.create

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentCreateKeyBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class CreateKeyFragment : BaseBindingFragment<CreateKeyViewModel, CreateKeyViewState, FragmentCreateKeyBinding>(), CreateKeyView {


    @Inject override lateinit var viewModelFactory: CreateKeyViewModelFactory

    override val layoutResId = R.layout.fragment_create_key
}
