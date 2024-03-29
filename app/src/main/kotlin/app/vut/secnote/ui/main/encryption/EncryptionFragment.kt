package app.vut.secnote.ui.main.encryption

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentEncryptionBinding
import app.vut.secnote.tools.Constants
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.tools.extensions.setResult
import app.vut.secnote.ui.base.BaseBindingFragment
import app.vut.secnote.ui.base.BaseSimpleDialogFragment
import com.thefuntasty.mvvm.livedata.observeNonNull
import javax.inject.Inject

class EncryptionFragment : BaseBindingFragment<EncryptionViewModel, EncryptionViewState, FragmentEncryptionBinding>(),
    EncryptionView, BaseSimpleDialogFragment.DialogListener {

    @Inject override lateinit var viewModelFactory: EncryptionViewModelFactory
    @Inject lateinit var adapter: EncryptionAdapter
    override val layoutResId = R.layout.fragment_encryption

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent(NavigateBackEvent::class) {
            navigateBack()
        }

        observeEvent(KeySelectionEvent::class) {
            setResult(Constants.Note.KEY_SELECTED, it.alias)
        }

        observeEvent(CreateKeyEvent::class) {
            navigateTo(
                EncryptionFragmentDirections.navigateToCreateKeyFragment()
            )
        }

        observeEvent(ShowEncryptionTutorialEvent::class) {
            navigateTo(
                EncryptionFragmentDirections.navigateToImageDialog(
                    message = resources.getString(R.string.general_encryption_tutorial),
                    imageSrc = R.drawable.ic_undraw_surveillance
                )
            )
        }

        binding.encryptionList.adapter = adapter
        viewModel.viewState.list.observeNonNull(this) {
            adapter.submitList(it)
        }
    }

    override fun onKeySelected(alias: String) {
        viewModel.onKeySelection(alias)
    }

    override fun onCancel(tag: String?) {
        viewModel.markTutorialAsSeen()
    }
}
