package app.vut.secnote.ui.main.error

import dagger.Module
import dagger.Provides

@Module
class ErrorDialogFragmentModule {
    @Provides
    fun arguments(fr: ErrorDialogFragment) = ErrorDialogFragmentArgs.fromBundle(fr.requireArguments())
}
