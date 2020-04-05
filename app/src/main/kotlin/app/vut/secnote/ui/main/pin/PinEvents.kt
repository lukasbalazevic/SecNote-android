package app.vut.secnote.ui.main.pin

import com.thefuntasty.mvvm.event.Event

sealed class PinEvent : Event<PinViewState>()
object AuthenticateDeviceEvent: PinEvent()
object ReauthenticateDeviceEvent: PinEvent()
object SetPinEvent : PinEvent()
object NavigateBackEvent: PinEvent()
object NavigateToNotesEvent: PinEvent()
