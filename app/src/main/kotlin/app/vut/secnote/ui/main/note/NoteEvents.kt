package app.vut.secnote.ui.main.note

import com.thefuntasty.mvvm.event.Event

sealed class NoteEvent : Event<NoteViewState>()
object NavigateBackEvent : NoteEvent()
object NoteSavedEvent : NoteEvent()
object AuthorizeDeviceEvent : NoteEvent()
object LogOutUserEvent : NoteEvent()
data class ErrorOccurredEvent(val title: String, val message: String) : NoteEvent()
object NoDecryptionKeyEvent : NoteEvent()
object BackPressConfirmEvent : NoteEvent()
object ShowDecryptionTutorialEvent : NoteEvent()
