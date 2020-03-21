package app.vut.secnote.ui.navigation

import app.vut.secnote.data.ui.StartDestination
import com.thefuntasty.mvvm.event.Event

sealed class NavigationEvent : Event<NavigationViewState>()
data class NavigationStartDestinationEvent(val destination: StartDestination) : NavigationEvent()