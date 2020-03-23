package app.vut.secnote.ui.main.encryption

import dagger.Module
import dagger.Provides

@Module
class EncryptionFragmentModule {
    @Provides
    fun view(fr: EncryptionFragment): EncryptionView = fr

    @Provides
    fun arguments(fr: EncryptionFragment) = EncryptionFragmentArgs.fromBundle(fr.requireArguments())
}
