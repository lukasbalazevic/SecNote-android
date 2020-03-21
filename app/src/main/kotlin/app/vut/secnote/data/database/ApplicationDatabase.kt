package app.vut.secnote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.vut.secnote.data.database.dao.NoteDao
import app.vut.secnote.data.model.room.Note
import app.vut.secnote.tools.Constants

@Database(
    version = Constants.Database.VERSION,
    entities = [Note::class]
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}
