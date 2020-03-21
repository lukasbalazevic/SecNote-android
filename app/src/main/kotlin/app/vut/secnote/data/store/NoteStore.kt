package app.vut.secnote.data.store

import app.vut.secnote.data.database.ApplicationDatabase
import app.vut.secnote.data.model.room.Note
import javax.inject.Inject

class NoteStore @Inject constructor(
    private val db: ApplicationDatabase
) {
    fun getNotes() = db.noteDao().getNotes()

    fun getNote(id: String) = db.noteDao().getNote(id)

    fun syncNotes(notes: List<Note>) = db.runInTransaction {
        deleteAll()
        insertNotes(notes)
    }

    private fun insertNotes(notes: List<Note>) = db.noteDao().insert(notes)

    private fun deleteAll() = db.noteDao().deleteAllNotes()
}