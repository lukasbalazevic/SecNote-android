package app.vut.secnote.ui.main.error

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ErrorDialogViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<ErrorDialogViewModel>
) : BaseViewModelFactory<ErrorDialogViewModel>() {
    override val viewModelClass: KClass<ErrorDialogViewModel> = ErrorDialogViewModel::class
}
