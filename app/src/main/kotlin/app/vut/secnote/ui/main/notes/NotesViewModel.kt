package app.vut.secnote.ui.main.notes

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    override val viewState: NotesViewState
) : BaseCrViewModel<NotesViewState>()

