package app.vut.secnote.ui.main.profile

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class ProfileViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<ProfileViewModel>
) : BaseViewModelFactory<ProfileViewModel>() {
    override val viewModelClass: KClass<ProfileViewModel> = ProfileViewModel::class
}
