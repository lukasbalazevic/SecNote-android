package app.vut.secnote.ui.main.notes

import app.vut.secnote.R
import app.vut.secnote.databinding.FragmentNotesBinding
import app.vut.secnote.ui.base.BaseBindingFragment
import javax.inject.Inject

class NotesFragment : BaseBindingFragment<NotesViewModel, NotesViewState, FragmentNotesBinding>(), NotesView {


    @Inject override lateinit var viewModelFactory: NotesViewModelFactory

    override val layoutResId = R.layout.fragment_notes
}
