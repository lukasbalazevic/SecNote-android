package app.vut.secnote.ui.main.note

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import app.vut.secnote.R
import app.vut.secnote.data.model.room.Note
import app.vut.secnote.tools.Constants
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import com.thefuntasty.mvvm.livedata.combineLiveData
import javax.inject.Inject

class NoteViewState @Inject constructor(
    private val resources: Resources
) : ViewState {

    val note = DefaultValueLiveData(Note.DEFAULT)
    val id = MutableLiveData<String?>(null)
    val categories = DefaultValueLiveData<List<String>>(listOf())
    val title = DefaultValueLiveData("")
    val body = DefaultValueLiveData("")
    val alias = DefaultValueLiveData("")

    val changed = combineLiveData(note, id, categories, title, body, alias) { note, id, categories, title, body, alias ->
        val noteCategories = note.categories
        val changeCategories = categories.joinToString(Constants.Database.CATEGORY_SEPARATOR)
        !(note.id == id && note.title == title && note.body == body && note.alias == alias && noteCategories == changeCategories)
    }

    val subtitle = changed.map {
        if (it) resources.getString(R.string.general_unsafe_changes) else ""
    }
}
