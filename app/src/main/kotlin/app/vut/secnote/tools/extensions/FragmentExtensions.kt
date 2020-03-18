package app.vut.secnote.tools.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
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

fun Fragment.forceShowKeyboard() {
    val ims = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    ims?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}

fun Fragment.forceHideKeyboard() {
    val ims = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    ims?.hideSoftInputFromWindow(this.view?.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
}
