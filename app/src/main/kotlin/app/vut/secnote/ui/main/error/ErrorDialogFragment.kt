package app.vut.secnote.ui.main.error

import android.os.Bundle
import android.view.View
import app.vut.secnote.BR
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentErrorDialogBinding
import app.vut.secnote.tools.extensions.navigateBack
import com.thefuntasty.mvvm.dagger.fragment.dialog.BaseDaggerBindingDialogFragment
import javax.inject.Inject

class ErrorDialogFragment : BaseDaggerBindingDialogFragment<ErrorDialogViewModel, ErrorDialogViewState, FragmentErrorDialogBinding>(), ErrorDialogView {

    override val brViewVariableId = BR.view
    override val brViewModelVariableId = BR.viewModel
    override val brViewStateVariableId = BR.viewState
    override val fragmentTag = this::class.java.simpleName

    @Inject override lateinit var viewModelFactory: ErrorDialogViewModelFactory

    override val layoutResId = R.layout.fragment_error_dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(NavigateBackEvent::class) {
            navigateBack()
        }
    }
}
