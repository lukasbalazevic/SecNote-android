package app.vut.secnote.ui.main.profile

import app.vut.secnote.tools.Constants
import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class ProfileViewState @Inject constructor() : ViewState {
    val email = DefaultValueLiveData("")

}
