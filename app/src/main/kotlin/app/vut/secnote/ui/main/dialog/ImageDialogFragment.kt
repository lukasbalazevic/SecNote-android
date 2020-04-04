package app.vut.secnote.ui.main.dialog

import android.os.Bundle
import android.view.View
import app.vut.secnote.BR
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentImageDialogBinding
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.ui.base.BaseSimpleDialogFragment
import com.thefuntasty.mvvm.dagger.fragment.dialog.BaseDaggerBindingDialogFragment
import javax.inject.Inject

class ImageDialogFragment : BaseDaggerBindingDialogFragment<ImageDialogViewModel, ImageDialogViewState, FragmentImageDialogBinding>(), ImageDialogView {

    override val brViewVariableId = BR.view
    override val brViewModelVariableId = BR.viewModel
    override val brViewStateVariableId = BR.viewState
    override val fragmentTag = this::class.java.simpleName

    @Inject override lateinit var viewModelFactory: ImageDialogViewModelFactory

    override val layoutResId = R.layout.fragment_image_dialog

    private val listener: BaseSimpleDialogFragment.DialogListener?
        get() = requireParentFragment().childFragmentManager.fragments.first() as? BaseSimpleDialogFragment.DialogListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(NavigateBackEvent::class) {
            navigateBack()
        }
    }

    override fun onStop() {
        listener?.onCancel("")
        super.onStop()
    }
}
