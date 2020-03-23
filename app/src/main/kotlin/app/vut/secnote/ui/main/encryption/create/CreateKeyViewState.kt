package app.vut.secnote.ui.main.encryption.create

import app.vut.secnote.data.model.ui.KeySize
import app.vut.secnote.ui.main.encryption.EncryptionFragmentArgs
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class CreateKeyViewState @Inject constructor(
) : ViewState {
    val alias = DefaultValueLiveData("")
    val size = DefaultValueLiveData(KeySize.AES256)
}
