package app.vut.secnote.ui.main.notes

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentNotesBinding
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.ui.base.BaseBindingFragment
import com.thefuntasty.mvvm.livedata.observeNonNull
import javax.inject.Inject

class NotesFragment : BaseBindingFragment<NotesViewModel, NotesViewState, FragmentNotesBinding>(), NotesView {

    @Inject override lateinit var viewModelFactory: NotesViewModelFactory
    @Inject lateinit var adapter: NotesAdapter

    override val layoutResId = R.layout.fragment_notes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.notesList.adapter = adapter
        viewModel.viewState.list.observeNonNull(this) {
            adapter.submitList(it)
        }

        observeEvent(NavigateToCreateOrUpdateNoteEvent::class) {
            navigateTo(
                NotesFragmentDirections.navigateToNote()
            )
        }
    }
}
