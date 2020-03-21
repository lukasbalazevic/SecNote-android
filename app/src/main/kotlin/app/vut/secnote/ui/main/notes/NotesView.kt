package app.vut.secnote.ui.main.notes

import app.vut.secnote.data.model.room.Note
import com.thefuntasty.mvvm.BaseView

interface NotesView : BaseView {
    fun onNoteClick(item: Note)
}
