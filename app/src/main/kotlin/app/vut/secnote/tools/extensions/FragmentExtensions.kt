package app.vut.secnote.tools.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import timber.log.Timber

fun Fragment.navigateBack() = findNavController().navigateUp()

fun Fragment.navigateBack(@IdRes destinationId: Int, inclusive: Boolean = false) =
    findNavController()
        .popBackStack(destinationId, inclusive)

fun Fragment.navigateTo(navDirections: NavDirections, options: NavOptions? = null) = try {
    findNavController().navigate(navDirections, options)
} catch (e: Exception) {
    Timber.e(e)
}
