package app.vut.secnote.ui.main.note

import androidx.lifecycle.MutableLiveData
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class NoteViewState @Inject constructor() : ViewState {

    val id = MutableLiveData<String?>(null)
    val categories = DefaultValueLiveData<List<String>>(listOf())
    val title = DefaultValueLiveData("")
    val body = DefaultValueLiveData("")
    val alias = DefaultValueLiveData("")
}
