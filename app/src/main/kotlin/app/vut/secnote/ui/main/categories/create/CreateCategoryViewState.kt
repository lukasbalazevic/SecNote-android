package app.vut.secnote.ui.main.categories.create

import com.thefuntasty.mvvm.ViewState
import com.thefuntasty.mvvm.livedata.DefaultValueLiveData
import javax.inject.Inject

class CreateCategoryViewState @Inject constructor() : ViewState {
    val name = DefaultValueLiveData("")
}
