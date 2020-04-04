package app.vut.secnote.ui.main.encryption

import androidx.lifecycle.map
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
    val loading = DefaultValueLiveData(true)
    val showEmptyState = combineLiveData(list, loading) {list, loading ->
        loading.not() && list.isEmpty()
    }
}
