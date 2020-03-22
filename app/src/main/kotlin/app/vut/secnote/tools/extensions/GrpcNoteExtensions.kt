package app.vut.secnote.tools.extensions

import app.vut.secnote.noteservice.Note
import app.vut.secnote.tools.Constants
import app.vut.secnote.data.model.room.Note as RoomNote

fun Note.convertToRoomNote() = RoomNote(
    this.id,
    this.title,
    this.body,
    this.encrypted,
    this.alias,
    this.categoriesList.joinToString(separator = Constants.Database.CATEGORY_SEPARATOR)
)