package app.vut.secnote.ui.main.categories

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class CategoriesViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<CategoriesViewModel>
) : BaseViewModelFactory<CategoriesViewModel>() {
    override val viewModelClass: KClass<CategoriesViewModel> = CategoriesViewModel::class
}
