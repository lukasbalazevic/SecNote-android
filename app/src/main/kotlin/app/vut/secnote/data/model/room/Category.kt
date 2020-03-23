package app.vut.secnote.data.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey
    val name: String
)
