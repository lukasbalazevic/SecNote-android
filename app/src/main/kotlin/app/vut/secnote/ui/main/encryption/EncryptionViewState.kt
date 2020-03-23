package app.vut.secnote.ui.main.encryption

import app.vut.secnote.data.model.ui.KeySelection
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import com.thefuntasty.mvvm.livedata.combineLiveData
import javax.inject.Inject

class EncryptionViewState @Inject constructor(
    arguments: EncryptionFragmentArgs
) : ViewState {
    val aliases = DefaultValueLiveData<List<String>>(emptyList())
    val selected = DefaultValueLiveData(arguments.alias)

    val list = combineLiveData(aliases, selected) { aliases, selected ->
        aliases.map {
            KeySelection(it, it == selected)
        }
    }
}
