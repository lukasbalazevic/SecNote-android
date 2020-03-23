package app.vut.secnote.data.model.ui

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteCategories(
    val categories: List<String>
) : Parcelable
