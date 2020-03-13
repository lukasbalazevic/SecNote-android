package app.vut.secnote.ui.splash

import com.thefuntasty.mvvm.factory.BaseViewModelFactory
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

class SplashViewModelFactory @Inject constructor(
    override val viewModelProvider: Provider<SplashViewModel>
) : BaseViewModelFactory<SplashViewModel>() {
    override val viewModelClass: KClass<SplashViewModel> = SplashViewModel::class
}
