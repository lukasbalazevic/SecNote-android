package app.vut.secnote.ui.main.note

import com.thefuntasty.mvvm.event.Event

sealed class NoteEvent : Event<NoteViewState>()
object NavigateBackEvent : NoteEvent()
object NoteSavedEvent : NoteEvent()
data class ErrorOccurredEvent(val title: String, val message: String) : NoteEvent()