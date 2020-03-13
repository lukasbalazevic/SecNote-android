package app.vut.secnote.injection.modules

import app.vut.secnote.ui.navigation.NavigationActivity
import app.vut.secnote.ui.navigation.NavigationActivityModule
import app.vut.secnote.ui.splash.SplashActivity
import app.vut.secnote.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [NavigationActivityModule::class])
    abstract fun mainActivity(): NavigationActivity

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun splashActivity(): SplashActivity
}
