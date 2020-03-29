package app.vut.secnote.ui.main.error

import com.thefuntasty.mvvm.event.Event

sealed class ErrorDialogEvent : Event<ErrorDialogViewState>()
object NavigateBackEvent : ErrorDialogEvent()
