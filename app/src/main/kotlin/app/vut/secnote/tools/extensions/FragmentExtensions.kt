package app.vut.secnote.tools.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.thefuntasty.mvvm.livedata.observeNonNull
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

fun Fragment.runOnUIThread(action: () -> Unit) {
    activity?.runOnUiThread(action)
}

fun <T> Fragment.setResult(key: String, value: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <T> Fragment.getResultOnce(owner: LifecycleOwner, key: String, onChanged: (T) -> Unit) {
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.observeNonNull(owner) {
        onChanged.invoke(it)
        findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(key)
    }
}
