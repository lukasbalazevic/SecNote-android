package app.vut.secnote.ui.main.dialog

import dagger.Module
import dagger.Provides

@Module
class ImageDialogFragmentModule {
    @Provides
    fun arguments(fr: ImageDialogFragment) = ImageDialogFragmentArgs.fromBundle(fr.requireArguments())
}
