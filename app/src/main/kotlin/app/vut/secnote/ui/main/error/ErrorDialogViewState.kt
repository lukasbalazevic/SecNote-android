package app.vut.secnote.ui.main.error

import android.content.res.Resources
import androidx.lifecycle.map
import app.vut.secnote.R
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class ErrorDialogViewState @Inject constructor(
    private val resources: Resources,
    args: ErrorDialogFragmentArgs
) : ViewState {
    val message = DefaultValueLiveData(
        args.message ?: resources.getString(R.string.general_error_connection)
    )
    val imageSrc = DefaultValueLiveData(args.imageSrc)
    val image = imageSrc.map {
        resources.getDrawable(it, null)
    }
}
