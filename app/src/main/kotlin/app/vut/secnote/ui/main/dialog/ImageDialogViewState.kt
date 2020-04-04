package app.vut.secnote.ui.main.dialog

import android.content.res.Resources
import androidx.lifecycle.map
import app.vut.secnote.R
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import timber.log.Timber
import javax.inject.Inject

class ImageDialogViewState @Inject constructor(
    private val resources: Resources,
    val args: ImageDialogFragmentArgs
) : ViewState {
    val message = DefaultValueLiveData(
        args.message ?: resources.getString(R.string.general_error_connection)
    )
    val imageSrc = DefaultValueLiveData(args.imageSrc)
    val image = imageSrc.map {
        resources.getDrawable(it, null)
    }
}
