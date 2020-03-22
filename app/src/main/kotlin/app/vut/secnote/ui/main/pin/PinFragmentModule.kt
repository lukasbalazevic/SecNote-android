package app.vut.secnote.ui.main.pin

import androidx.biometric.BiometricPrompt
import app.vut.secnote.tools.security.BiometricCallback
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors

@Module
class PinFragmentModule {

    @Provides
    fun arguments(fragment: PinFragment) = PinFragmentArgs.fromBundle(fragment.requireArguments())

    @Provides
    fun callback(fragment: PinFragment) = BiometricCallback(fragment)

    @Provides
    fun biometricPrompt(fragment: PinFragment, callback: BiometricCallback): BiometricPrompt =
        BiometricPrompt(fragment, Executors.newSingleThreadExecutor(), callback)
}
