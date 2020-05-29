package app.vut.secnote.ui.main.notes

import android.os.Bundle
import android.view.View
import app.vut.secnote.R
import app.vut.secnote.data.model.room.Note
import app.vut.secnote.data.model.ui.PinState
import app.vut.secnote.databinding.FragmentNotesBinding
import app.vut.secnote.tools.extensions.navigateTo
import app.vut.secnote.ui.base.BaseBindingFragment
import app.vut.secnote.ui.base.BaseSimpleDialogFragment
import app.vut.secnote.ui.main.note.NoteFragment
import app.vut.secnote.ui.main.note.NoteFragmentDirections
import com.thefuntasty.mvvm.livedata.observeNonNull
import javax.inject.Inject

class NotesFragment : BaseBindingFragment<NotesViewModel, NotesViewState, FragmentNotesBinding>(), NotesView, BaseSimpleDialogFragment.DialogListener {

    @Inject override lateinit var viewModelFactory: NotesViewModelFactory
    @Inject lateinit var adapter: NotesAdapter

    override val layoutResId = R.layout.fragment_notes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setBottomBarMenuClicks()
        binding.notesList.adapter = adapter
        viewModel.viewState.list.observeNonNull(this) {
            adapter.submitList(it)
        }

        observeEvent(NavigateToCreateOrUpdateNoteEvent::class) {
            navigateTo(
                NotesFragmentDirections.navigateToNoteFragment(it.note)
            )
        }

        observeEvent(ShowErrorEvent::class) {
            navigateTo(
                NotesFragmentDirections.navigateToImageDialog(
                    message = it.message,
                    imageSrc = it.imageSrc
                )
            )
        }

        observeEvent(AuthorizeDeviceEvent::class) {
            navigateTo(
                NotesFragmentDirections.navigateToPinFragment(PinState.REAUTHORISE)
            )
        }

        observeEvent(LogOutUserEvent::class) {
            navigateTo(
                NoteFragmentDirections.navigateToDialog(
                    dialogTitle = resources.getString(R.string.general_error_unauthenticated),
                    dialogBody = resources.getString(R.string.general_error_unauthenticated_body),
                    dialogNegative = resources.getString(R.string.general_ok)
                )
            )
        }
    }

    private fun setBottomBarMenuClicks() {
        binding.bar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.notes_profile -> navigateTo(
                    NotesFragmentDirections.navigateToProfileFragment()
                )
            }
            true
        }
    }

    override fun onNoteClick(item: Note) {
        navigateTo(
            NotesFragmentDirections.navigateToNoteFragment(item.id)
        )
    }

    override fun onCancel(tag: String?) {
        navigateTo(
            NoteFragmentDirections.navigateToLoginFragment()
        )
    }
}
