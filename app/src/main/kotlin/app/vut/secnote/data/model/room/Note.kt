package app.vut.secnote.data.model.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import app.vut.secnote.tools.Constants

@Entity
data class Note(
    @PrimaryKey
    val id: String,
    val title: String,
    val body: String,
    val encrypted: Boolean,
    val alias: String,
    val categories: String
) {
    @Ignore
    val categoryList = if (categories.isBlank()) emptyList() else categories.split(Constants.Database.CATEGORY_SEPARATOR)

    @Ignore
    val bodyPreview = if (body.length > Constants.Note.NOTE_BODY_PREVIEW_LENGTH)
        "${body.subSequence(0, Constants.Note.NOTE_BODY_PREVIEW_LENGTH)}..."
    else
        body
}