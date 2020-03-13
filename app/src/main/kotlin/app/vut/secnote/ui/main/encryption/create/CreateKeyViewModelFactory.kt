package app.vut.secnote.ui.main.encryption.create

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class CreateKeyViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<CreateKeyViewModel>
) : BaseViewModelFactory<CreateKeyViewModel>() {
    override val viewModelClass: KClass<CreateKeyViewModel> = CreateKeyViewModel::class
}
