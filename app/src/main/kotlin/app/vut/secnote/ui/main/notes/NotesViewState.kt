package app.vut.secnote.ui.main.notes

import app.vut.secnote.data.model.room.Note
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import com.thefuntasty.mvvm.livedata.combineLiveData
import javax.inject.Inject

class NotesViewState @Inject constructor() : ViewState {

    val loading = DefaultValueLiveData(true)
    val list = DefaultValueLiveData<List<Note>>(emptyList())
    val showEmptyState = combineLiveData(loading, list) { loading, list ->
        list.isEmpty() && loading.not()
    }

    val showList = combineLiveData(loading, list) { loading, list ->
        list.isNotEmpty() && loading.not()
    }
}
