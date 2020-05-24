package app.vut.secnote.ui.main.pin

import android.content.res.Resources
import androidx.biometric.BiometricPrompt
import app.vut.secnote.R
import app.vut.secnote.tools.security.BiometricCallback
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Module
class PinFragmentModule {
    @Provides
    fun arguments(fragment: PinFragment) = PinFragmentArgs.fromBundle(fragment.requireArguments())

    @Provides
    fun callback(fragment: PinFragment) = BiometricCallback(fragment)

    @Provides
    fun biometricPromptInfo(resources: Resources) = BiometricPrompt.PromptInfo.Builder()
        .setTitle(resources.getString(R.string.general_lock_screen_title))
        .setSubtitle(resources.getString(R.string.general_lock_screen_subtitle))
        .setDeviceCredentialAllowed(true)
        .build()

    @Provides
    fun executor() = Executors.newSingleThreadExecutor()

    @Provides
    fun biometricPrompt(fragment: PinFragment, callback: BiometricCallback, executor: ExecutorService): BiometricPrompt =
        BiometricPrompt(fragment, executor, callback)
}
