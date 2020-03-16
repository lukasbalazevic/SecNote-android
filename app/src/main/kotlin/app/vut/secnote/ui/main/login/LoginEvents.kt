package app.vut.secnote.ui.main.login

import com.thefuntasty.mvvm.event.Event

sealed class LoginEvent : Event<LoginViewState>()
object NavigateToNotesEvent : LoginEvent()
data class ShowErrorEvent(val title: String, val body: String) : LoginEvent()
