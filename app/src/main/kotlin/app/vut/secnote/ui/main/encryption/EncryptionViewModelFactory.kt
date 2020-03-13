package app.vut.secnote.ui.main.encryption

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class EncryptionViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<EncryptionViewModel>
) : BaseViewModelFactory<EncryptionViewModel>() {
    override val viewModelClass: KClass<EncryptionViewModel> = EncryptionViewModel::class
}
