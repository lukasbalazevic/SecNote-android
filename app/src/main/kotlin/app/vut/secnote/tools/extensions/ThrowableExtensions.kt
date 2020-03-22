package app.vut.secnote.tools.extensions

import android.security.keystore.UserNotAuthenticatedException

fun Throwable.checkForUserNoteAuthenticatedException(authorize: () -> Unit, showError: () -> Unit) {
    if (this is UserNotAuthenticatedException) {
        authorize()
    } else {
        showError()
    }
}