package app.vut.secnote.domain.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import app.vut.secnote.tools.Constants
import com.google.common.io.BaseEncoding
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.MessageDigest
import java.security.Signature
import javax.inject.Inject

class CryptoHelper @Inject constructor(
    private val keystore: KeyStore,
    private val keyPairGenerator: KeyPairGenerator
) {

    companion object {
        const val PEM_KEY_RREFIX = "-----BEGIN RSA PUBLIC KEY-----"
        const val PEM_KEY_POSFIX = "-----END RSA PUBLIC KEY-----"
    }

    fun generateKey(): String {
        val params = KeyGenParameterSpec
            .Builder(Constants.Security.DEVICE_USER_KEY, KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY)
            .setDigests(KeyProperties.DIGEST_SHA512)
            .setKeySize(Constants.Security.DEVICE_USER_KEY_SIG_SIZE)
            .setUserAuthenticationRequired(true)
            .setUserAuthenticationValidityDurationSeconds(60 * 5)
            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
            .build()

        keyPairGenerator.initialize(params)
        val kP = keyPairGenerator.generateKeyPair()
        val encoded = BaseEncoding.base64().encode(kP.public.encoded)
        val pem = "$PEM_KEY_RREFIX\n$encoded\n$PEM_KEY_POSFIX"

        return encodeBase64(pem.toByteArray())
    }

    fun checkIfDeviceKeyExists() =
        keystore.getEntry(Constants.Security.DEVICE_USER_KEY, null) != null

    fun getDeviceKey(): String {
        val entry = keystore.getEntry(Constants.Security.DEVICE_USER_KEY, null) as KeyStore.PrivateKeyEntry
        val encoded = BaseEncoding.base64().encode(entry.certificate.publicKey.encoded)
        val pem = "$PEM_KEY_RREFIX\n$encoded\n$PEM_KEY_POSFIX"

        return encodeBase64(pem.toByteArray())
    }

    fun hashMessage(message: ByteArray): String =
        MessageDigest.getInstance(Constants.Security.HASH_ALG).run {
            update(message)
            encodeBase64(digest())
        }

    fun signAndEncodeDataBase64(data: ByteArray): String {
        val entry = keystore.getEntry(Constants.Security.DEVICE_USER_KEY, null) as KeyStore.PrivateKeyEntry
        return Signature.getInstance(Constants.Security.DEVICE_USER_KEY_SIG_ALG)
            .run {
                initSign(entry.privateKey)
                update(data)
                encodeBase64(sign())
            }
    }

    fun encodeBase64(data: ByteArray) = BaseEncoding.base64().encode(data)
}