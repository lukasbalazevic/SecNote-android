package app.vut.secnote.ui.main.note

import com.thefuntasty.mvvm.event.Event

sealed class NoteEvent : Event<NoteViewState>()
object NavigateBackEvent : NoteEvent()
