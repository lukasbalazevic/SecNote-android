package app.vut.secnote.injection.components

import app.vut.secnote.App
import app.vut.secnote.injection.modules.ActivityBuilderModule
import app.vut.secnote.injection.modules.ApplicationModule
import app.vut.secnote.injection.modules.FragmentBuilderModule
import app.vut.secnote.injection.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class,
    NetworkModule::class
]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>
}
