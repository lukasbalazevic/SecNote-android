package app.vut.secnote.tools.extensions

import android.security.keystore.UserNotAuthenticatedException
import app.vut.secnote.data.model.error.AppError
import app.vut.secnote.data.model.error.UnauthenticatedError
import timber.log.Timber

fun Throwable.checkForUserNoteAuthenticatedException(authorize: () -> Unit, logOutUser: (e: UnauthenticatedError) -> Unit ,showError: (e: AppError) -> Unit) {
    when (this) {
        is UserNotAuthenticatedException -> authorize()
        is UnauthenticatedError -> logOutUser(this)
        is AppError -> showError(this)
        else -> {
            Timber.d(this)
            error("Invalid error type")
        }
    }
}
