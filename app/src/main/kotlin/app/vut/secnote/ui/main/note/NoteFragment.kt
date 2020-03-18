package app.vut.secnote.ui.main.note

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentNoteBinding
import app.vut.secnote.tools.extensions.forceHideKeyboard
import app.vut.secnote.tools.extensions.navigateBack
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.ui.base.BaseBindingFragment
import app.vut.secnote.ui.main.notes.NotesFragmentDirections
import com.thefuntasty.mvvm.livedata.observe
import javax.inject.Inject

class NoteFragment : BaseBindingFragment<NoteViewModel, NoteViewState, FragmentNoteBinding>(), NoteView {

    @Inject override lateinit var viewModelFactory: NoteViewModelFactory
    @Inject lateinit var adapter: CategoriesAdapter
    override val layoutResId = R.layout.fragment_note

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.noteCategories.adapter = adapter
        viewModel.viewState.categories.observe(this) {
            adapter.submitList(it)
        }
        observeEvent(NavigateBackEvent::class) {
            forceHideKeyboard()
            navigateBack()
        }
    }

    override fun addCategory() {
        navigateTo(
            NoteFragmentDirections.navigateToCreateCategory()
        )
    }
}
