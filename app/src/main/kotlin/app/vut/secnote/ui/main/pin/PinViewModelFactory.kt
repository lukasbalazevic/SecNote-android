package app.vut.secnote.ui.main.pin

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class PinViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<PinViewModel>
) : BaseViewModelFactory<PinViewModel>() {
    override val viewModelClass: KClass<PinViewModel> = PinViewModel::class
}
