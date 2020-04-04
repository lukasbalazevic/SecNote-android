package app.vut.secnote.ui.main.note

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import app.vut.secnote.R
import app.vut.secnote.data.model.ui.NoteCategories
import app.vut.secnote.data.model.ui.PinState
import app.vut.secnote.databinding.FragmentNoteBinding
import app.vut.secnote.tools.Constants
import app.vut.secnote.tools.extensions.forceHideKeyboard
import app.vut.secnote.tools.extensions.getResultOnce
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.ui.base.BaseBindingFragment
import app.vut.secnote.ui.base.BaseSimpleDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.thefuntasty.mvvm.livedata.observe
import javax.inject.Inject

class NoteFragment : BaseBindingFragment<NoteViewModel, NoteViewState, FragmentNoteBinding>(),
    NoteView, BaseSimpleDialogFragment.DialogListener {

    @Inject override lateinit var viewModelFactory: NoteViewModelFactory
    @Inject lateinit var adapter: CategoriesAdapter
    override val layoutResId = R.layout.fragment_note

    companion object {
        const val KEY_NOT_FOUND_DIALOG_TAG = "KEY_NOT_FOUND_DIALOG_TAG"
        const val CONFIRM_BACK_PRESS_DIALOG_TAG = "CONFIRM_BACK_PRESS_DIALOG_TAG"
        const val DECRYPTION_TUTORIAL = "DECRYPTION_TUTORIAL"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setOnBackPressCallback()
        setBottomBarMenuClicks()
        binding.noteCategories.adapter = adapter

        viewModel.viewState.categories.observe(this) {
            adapter.submitList(it)
        }

        getResultOnce<NoteCategories>(this, Constants.Note.CATEGORIES_CHANGE) {
            viewModel.viewState.categories.value = it.categories
        }

        getResultOnce<String>(this, Constants.Note.KEY_SELECTED) {
            showEncryptionChange(it)
            viewModel.viewState.alias.value = it
        }

        observeEvent(NavigateBackEvent::class) {
            forceHideKeyboard()
            navigateBack()
        }

        observeEvent(BackPressConfirmEvent::class) {
            forceHideKeyboard()
            navigateTo(
                NoteFragmentDirections.navigateToDialog(
                    dialogTag = CONFIRM_BACK_PRESS_DIALOG_TAG,
                    dialogTitle = resources.getString(R.string.general_unsafe_changes),
                    dialogBody = resources.getString(R.string.general_unsafe_changes_info),
                    dialogPositive = resources.getString(R.string.general_discard),
                    dialogNegative = resources.getString(R.string.general_cancel)
                )
            )
        }

        observeEvent(NoteSavedEvent::class) {
            Snackbar.make(binding.root, resources.getString(R.string.general_note_saved), Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.notesSaveFab)
                .show()
        }

        observeEvent(ErrorOccurredEvent::class) {
            Snackbar.make(binding.root, "${it.title} ${it.message}", Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.notesSaveFab)
                .show()
        }

        observeEvent(NoDecryptionKeyEvent::class) {
            navigateTo(
                NoteFragmentDirections.navigateToDialog(
                    dialogTag = KEY_NOT_FOUND_DIALOG_TAG,
                    dialogTitle = resources.getString(R.string.general_note_key_not_found_title),
                    dialogBody = resources.getString(R.string.general_note_key_not_found_body),
                    dialogPositive = resources.getString(R.string.general_ok)
                )
            )
        }

        observeEvent(AuthorizeDeviceEvent::class) {
            navigateTo(
                NoteFragmentDirections.navigateToPinFragment(PinState.REAUTHORISE)
            )
        }

        observeEvent(LogOutUserEvent::class) {
            navigateTo(
                NoteFragmentDirections.navigateToLoginFragment()
            )
        }

        observeEvent(ShowDecryptionTutorialEvent::class) {
            navigateTo(
                NoteFragmentDirections.navigateToImageDialog(
                    dialogTag = DECRYPTION_TUTORIAL,
                    message = resources.getString(R.string.general_decryption_tutorial),
                    imageSrc = R.drawable.ic_undraw_security_on
                )
            )
        }
    }

    override fun addCategory() {
        navigateTo(
            NoteFragmentDirections.navigateToCategoriesFragment(
                NoteCategories(viewModel.viewState.categories.value)
            )
        )
    }

    private fun showEncryptionChange(key: String) {
        val message = if (key.isBlank()) {
            resources.getString(R.string.general_encryption_removed)
        } else {
            resources.getString(R.string.general_encryption_added, key)
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.notesSaveFab)
            .show()
    }

    private fun setBottomBarMenuClicks() {
        binding.noteAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.note_lock -> {
                    navigateTo(
                        NoteFragmentDirections.navigateToEncryptionFragment(viewModel.viewState.alias.value)
                    )
                }
                R.id.note_delete -> viewModel.deleteNote()
            }
            true
        }
    }

    private fun setOnBackPressCallback() {
        activity?.onBackPressedDispatcher?.addCallback(owner = this, onBackPressed = {
            viewModel.navigateBack()
        })
    }

    override fun onPositive(tag: String?) {
        when (tag) {
            CONFIRM_BACK_PRESS_DIALOG_TAG -> navigateBack(R.id.fragment_notes)
        }
    }

    override fun onCancel(tag: String?) {
        when (tag) {
            KEY_NOT_FOUND_DIALOG_TAG -> viewModel.deleteNote()
            DECRYPTION_TUTORIAL -> viewModel.markDecryptionTutorial()
        }
    }
}
