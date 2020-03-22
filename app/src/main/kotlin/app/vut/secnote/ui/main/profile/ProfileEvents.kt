package app.vut.secnote.ui.main.profile

import com.thefuntasty.mvvm.event.Event

sealed class ProfileEvent : Event<ProfileViewState>()
object NavigateBackEvent : ProfileEvent()
object NavigateToLoginEvent : ProfileEvent()
object ErrorOccurredEvent : ProfileEvent()