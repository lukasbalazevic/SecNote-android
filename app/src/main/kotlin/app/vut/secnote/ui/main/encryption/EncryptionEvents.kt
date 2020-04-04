package app.vut.secnote.ui.main.encryption

import com.thefuntasty.mvvm.event.Event

sealed class EncryptionEvent : Event<EncryptionViewState>()
object NavigateBackEvent : EncryptionEvent()
data class KeySelectionEvent(val alias: String) : EncryptionEvent()
object CreateKeyEvent : EncryptionEvent()
object ShowEncryptionTutorialEvent : EncryptionEvent()
