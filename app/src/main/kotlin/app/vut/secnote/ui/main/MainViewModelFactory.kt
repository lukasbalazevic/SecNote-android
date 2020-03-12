package app.vut.secnote.ui.main

import app.vut.secnote.ui.main.MainViewModel
import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class MainViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<MainViewModel>
) : BaseViewModelFactory<MainViewModel>() {
    override val viewModelClass: KClass<MainViewModel> = MainViewModel::class
}
