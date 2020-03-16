package app.vut.secnote.ui.main.profile

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    override val viewState: ProfileViewState
) : BaseCrViewModel<ProfileViewState>()
