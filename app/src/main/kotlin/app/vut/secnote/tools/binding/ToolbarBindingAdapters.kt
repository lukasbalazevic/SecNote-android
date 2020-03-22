package app.vut.secnote.tools.binding

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter("app:navigation_icon_click")
fun Toolbar.navigationIconClick(listener: View.OnClickListener) {
    setNavigationOnClickListener(listener)
}