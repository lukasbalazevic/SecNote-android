package app.vut.secnote.ui.main.note

import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class NoteViewState @Inject constructor() : ViewState {
    val categories = DefaultValueLiveData<List<String>>(listOf())
    val title = DefaultValueLiveData("")
    val body = DefaultValueLiveData("")
}
