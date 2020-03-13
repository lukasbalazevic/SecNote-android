package app.vut.secnote.ui.main.categories.create

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class CreateCategoryViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<CreateCategoryViewModel>
) : BaseViewModelFactory<CreateCategoryViewModel>() {
    override val viewModelClass: KClass<CreateCategoryViewModel> = CreateCategoryViewModel::class
}
