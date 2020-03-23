package app.vut.secnote.ui.main.note

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.data.model.ui.NoteCategories
import app.vut.secnote.databinding.FragmentNoteBinding
import app.vut.secnote.tools.Constants
import app.vut.secnote.tools.extensions.forceHideKeyboard
import app.vut.secnote.tools.extensions.getResultOnce
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.ui.base.BaseBindingFragment
import com.google.android.material.snackbar.Snackbar
import com.thefuntasty.mvvm.livedata.observe
import javax.inject.Inject

class NoteFragment : BaseBindingFragment<NoteViewModel, NoteViewState, FragmentNoteBinding>(), NoteView {

    @Inject override lateinit var viewModelFactory: NoteViewModelFactory
    @Inject lateinit var adapter: CategoriesAdapter
    override val layoutResId = R.layout.fragment_note

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setBottomBarMenuClicks()
        binding.noteCategories.adapter = adapter

        viewModel.viewState.categories.observe(this) {
            adapter.submitList(it)
        }

        getResultOnce<NoteCategories>(this, Constants.Note.CATEGORIES_CHANGE) {
            viewModel.viewState.categories.value = it.categories
        }

        getResultOnce<String>(this, Constants.Note.KEY_SELECTED) {
            viewModel.viewState.alias.value = it
        }

        observeEvent(NavigateBackEvent::class) {
            forceHideKeyboard()
            navigateBack()
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
    }

    override fun addCategory() {
        navigateTo(
            NoteFragmentDirections.navigateToCategoriesFragment(
                NoteCategories(viewModel.viewState.categories.value)
            )
        )
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
                R.id.note_share -> {
                }
            }
            true
        }
    }
}
