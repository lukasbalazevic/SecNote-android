package app.vut.secnote.data.model.error

import app.vut.secnote.R

sealed class AppError(
    val body: Int,
    val imageSrc: Int,
    cause: Throwable
) : Exception(cause)

class NoConnectionError(exception: Throwable) : AppError(
    body = R.string.general_error_connection,
    imageSrc = R.drawable.ic_server_down,
    cause = exception
)

class UserNotFoundError(exception: Throwable) : AppError(
    body = R.string.general_error_user_not_found,
    imageSrc = R.drawable.ic_invalid_credentials,
    cause = exception
)

class InvalidCredentialsError(exception: Throwable) : AppError(
    body = R.string.general_error_invalid_credentials,
    imageSrc = R.drawable.ic_invalid_credentials,
    cause = exception
)

class UnknownAppError(exception: Throwable) : AppError(
    body = R.string.general_error_unknown,
    imageSrc = R.drawable.ic_server_down,
    cause = exception
)

class UnauthenticatedError(exception: Throwable) : AppError(
    body = R.string.general_error_unauthenticated,
    imageSrc = R.drawable.ic_undraw_unlock,
    cause = exception
)
