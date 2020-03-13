package app.vut.secnote.ui.main.encryption

import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class EncryptionViewModel @Inject constructor(
    override val viewState: EncryptionViewState
) : BaseCrViewModel<EncryptionViewState>()

