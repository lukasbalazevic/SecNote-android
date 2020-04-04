package app.vut.secnote.ui.main.dialog

import com.thefuntasty.mvvm.event.Event

sealed class ImageDialogEvent : Event<ImageDialogViewState>()
object NavigateBackEvent : ImageDialogEvent()
