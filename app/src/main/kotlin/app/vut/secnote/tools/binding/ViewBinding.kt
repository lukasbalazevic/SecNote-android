package app.vut.secnote.tools.binding

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("app:visibility")
fun View.visibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}