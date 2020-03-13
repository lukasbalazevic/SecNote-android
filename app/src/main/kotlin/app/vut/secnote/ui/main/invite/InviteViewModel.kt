package app.vut.secnote.ui.main.invite

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class InviteViewModel @Inject constructor(
    override val viewState: InviteViewState
) : BaseCrViewModel<InviteViewState>()

