package app.vut.secnote.ui.main.login

import app.vut.secnote.data.model.ui.PinState
import com.thefuntasty.mvvm.event.Event

sealed class LoginEvent : Event<LoginViewState>()
data class NavigateToPinEvent(val state: PinState): LoginEvent()
data class ShowErrorEvent(val title: String, val body: String) : LoginEvent()