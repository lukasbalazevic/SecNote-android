package app.vut.secnote.tools.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:errorText")
fun TextInputLayout.setErrorMessage(message: String) {
    error = message
}