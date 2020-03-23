package app.vut.secnote.ui.main.categories

import dagger.Module
import dagger.Provides

@Module
class CategoriesFragmentModule {
    @Provides
    fun arguments(fr: CategoriesFragment) = CategoriesFragmentArgs.fromBundle(fr.requireArguments())
}
