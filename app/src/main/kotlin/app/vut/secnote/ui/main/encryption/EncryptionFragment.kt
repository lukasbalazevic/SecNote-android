package app.vut.secnote.ui.main.encryption

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentEncryptionBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class EncryptionFragment : BaseBindingFragment<EncryptionViewModel, EncryptionViewState, FragmentEncryptionBinding>(), EncryptionView {


    @Inject override lateinit var viewModelFactory: EncryptionViewModelFactory

    override val layoutResId = R.layout.fragment_encryption
}
