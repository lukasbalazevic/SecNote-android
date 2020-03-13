package app.vut.secnote.ui.main.pin

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentPinBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class PinFragment : BaseBindingFragment<PinViewModel, PinViewState, FragmentPinBinding>(), PinView {


    @Inject override lateinit var viewModelFactory: PinViewModelFactory

    override val layoutResId = R.layout.fragment_pin
}
