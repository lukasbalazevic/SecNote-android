package app.vut.secnote.ui.main.pin

import android.content.res.Resources
import androidx.lifecycle.map
import app.vut.secnote.R
import app.vut.secnote.data.model.ui.PinState
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class PinViewState @Inject constructor(
    arguments: PinFragmentArgs,
    resources: Resources
) : ViewState {

    val loading = DefaultValueLiveData(true)
    val state = DefaultValueLiveData(arguments.pinState)
    val infoText = state.map {
        when(it) {
            PinState.PIN_SET -> resources.getString(R.string.general_set_pin_info)
            PinState.AUTHORISE -> resources.getString(R.string.general_lock_screen_title)
            PinState.REAUTHORISE -> resources.getString(R.string.general_authorize_expired)
        }
    }

    val buttonText = state.map {
        when(it) {
            PinState.PIN_SET -> resources.getString(R.string.general_set_pin)
            PinState.AUTHORISE -> resources.getString(R.string.general_authorize_device)
            PinState.REAUTHORISE -> resources.getString(R.string.general_authorize_device)
        }
    }
}
