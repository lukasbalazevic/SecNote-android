package app.vut.secnote.ui.main.encryption.create

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class CreateKeyViewModel @Inject constructor(
    override val viewState: CreateKeyViewState
) : BaseCrViewModel<CreateKeyViewState>()

