package app.vut.secnote.tools.extensions

import android.security.keystore.UserNotAuthenticatedException
import app.vut.secnote.data.model.error.AppError
import app.vut.secnote.data.model.error.InvalidCredentialsError
import app.vut.secnote.data.model.error.UnknownAppError
import timber.log.Timber

fun Throwable.checkForUserNoteAuthenticatedException(authorize: () -> Unit, logOutUser: (e: InvalidCredentialsError) -> Unit ,showError: (e: AppError) -> Unit) {
    when (this) {
        is UserNotAuthenticatedException -> authorize()
        is InvalidCredentialsError -> logOutUser(this)
        is AppError -> showError(this)
        else -> {
            Timber.d(this)
            showError(UnknownAppError(this))
        }
    }
}
