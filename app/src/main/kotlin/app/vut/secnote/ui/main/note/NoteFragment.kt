package app.vut.secnote.ui.main.note

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentNoteBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class NoteFragment : BaseBindingFragment<NoteViewModel, NoteViewState, FragmentNoteBinding>(), NoteView {


    @Inject override lateinit var viewModelFactory: NoteViewModelFactory

    override val layoutResId = R.layout.fragment_note
}
