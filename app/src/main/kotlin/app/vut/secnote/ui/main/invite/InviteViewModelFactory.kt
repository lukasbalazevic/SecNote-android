package app.vut.secnote.ui.main.invite

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class InviteViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<InviteViewModel>
) : BaseViewModelFactory<InviteViewModel>() {
    override val viewModelClass: KClass<InviteViewModel> = InviteViewModel::class
}
