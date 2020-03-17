package app.vut.secnote.ui.main.notes

import androidx.lifecycle.map
import app.vut.secnote.noteservice.Note
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class NotesViewState @Inject constructor() : ViewState {
    val list = DefaultValueLiveData<List<Note>>(emptyList())
    val isListEmpty = list.map { it.isEmpty() }
}
