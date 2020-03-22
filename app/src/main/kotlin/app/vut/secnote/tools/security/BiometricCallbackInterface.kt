package app.vut.secnote.tools.security

import androidx.biometric.BiometricPrompt

interface BiometricCallbackInterface {
    fun onAuthenticationError(errorCode: Int, errString: CharSequence)
    fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult)
    fun onAuthenticationFailed()
}