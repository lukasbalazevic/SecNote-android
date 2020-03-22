package app.vut.secnote.ui.main.notes

import com.thefuntasty.mvvm.event.Event

sealed class NotesEvent : Event<NotesViewState>()
data class NavigateToCreateOrUpdateNoteEvent(val note: String? = null) : NotesEvent()
object AuthorizeDeviceEvent : NotesEvent()