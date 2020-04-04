package app.vut.secnote.ui.main.dialog

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ImageDialogViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<ImageDialogViewModel>
) : BaseViewModelFactory<ImageDialogViewModel>() {
    override val viewModelClass: KClass<ImageDialogViewModel> = ImageDialogViewModel::class
}
