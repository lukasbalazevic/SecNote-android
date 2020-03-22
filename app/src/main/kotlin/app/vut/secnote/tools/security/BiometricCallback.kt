package app.vut.secnote.tools.security

import androidx.biometric.BiometricPrompt
import javax.inject.Inject

class BiometricCallback @Inject constructor(val callback: BiometricCallbackInterface) : BiometricPrompt.AuthenticationCallback() {
    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
        callback.onAuthenticationError(errorCode, errString)
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        callback.onAuthenticationSucceeded(result)
    }

    override fun onAuthenticationFailed() {
        callback.onAuthenticationFailed()
    }
}