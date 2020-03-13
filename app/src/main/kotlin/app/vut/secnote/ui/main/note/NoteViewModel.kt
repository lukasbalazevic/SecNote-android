package app.vut.secnote.ui.main.note

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    override val viewState: NoteViewState
) : BaseCrViewModel<NoteViewState>()

