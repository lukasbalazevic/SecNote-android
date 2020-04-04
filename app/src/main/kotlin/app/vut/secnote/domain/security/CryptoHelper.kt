package app.vut.secnote.domain.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.MasterKeys
import app.vut.secnote.tools.Constants
import app.vut.secnote.tools.extensions.second
import com.google.common.io.BaseEncoding
import java.lang.IllegalStateException
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.MessageDigest
import java.security.PrivateKey
import java.security.Signature
import java.security.UnrecoverableKeyException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class CryptoHelper @Inject constructor(
    private val keystore: KeyStore,
    private val keyPairGenerator: KeyPairGenerator
) {

    companion object {
        const val PEM_KEY_RREFIX = "-----BEGIN RSA PUBLIC KEY-----"
        const val PEM_KEY_POSFIX = "-----END RSA PUBLIC KEY-----"
        const val ENCRYPTION_DELIMITER = ";-;"
    }

    fun generateKey(): String {
        val params = KeyGenParameterSpec
            .Builder(Constants.Security.DEVICE_USER_KEY, KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY)
            .setDigests(KeyProperties.DIGEST_SHA512)
            .setKeySize(Constants.Security.DEVICE_USER_KEY_SIG_SIZE)
            .setUserAuthenticationRequired(true)
            .setUserAuthenticationValidityDurationSeconds(Constants.Security.DEVICE_AUTHORIZATION_WINDOW)
            .setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
            .build()

        keyPairGenerator.initialize(params)
        val kP = keyPairGenerator.generateKeyPair()
        val encoded = BaseEncoding.base64().encode(kP.public.encoded)
        val pem = "$PEM_KEY_RREFIX\n$encoded\n$PEM_KEY_POSFIX"

        return encodeBase64(pem.toByteArray())
    }

    fun checkIfDeviceKeyExists() = try {
        keystore.getCertificate(Constants.Security.DEVICE_USER_KEY) != null
    } catch (e: UnrecoverableKeyException) {
        deleteDeviceKey()
        false
    }


    private fun deleteDeviceKey() = keystore.deleteEntry(Constants.Security.DEVICE_USER_KEY)

    fun getDeviceKey(): String {
        val entry = keystore.getCertificate(Constants.Security.DEVICE_USER_KEY) ?: return generateKey()
        val encoded = BaseEncoding.base64().encode(entry.publicKey.encoded)
        val pem = "$PEM_KEY_RREFIX\n$encoded\n$PEM_KEY_POSFIX"

        return encodeBase64(pem.toByteArray())
    }

    fun getKeystoreAliases() = keystore.aliases().toList()
        .filter {
            it != Constants.Security.DEVICE_USER_KEY &&
                it != MasterKeys.AES256_GCM_SPEC.keystoreAlias
        }

    fun generateEncryptionKey(alias: String, keySize: Int) {
        val keyGenerator = KeyGenerator
            .getInstance(Constants.Security.AES, Constants.Security.KEYSTORE)
        val keyGenParameterSpec =
            KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .apply {
                    setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    setRandomizedEncryptionRequired(true)
                    setUserAuthenticationRequired(true)
                    setUserAuthenticationValidityDurationSeconds(Constants.Security.DEVICE_AUTHORIZATION_WINDOW)
                }
                .setKeySize(keySize)
                .build()
        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
    }

    fun encryptData(alias: String, data: String): String {
        val key = keystore.getKey(alias, null)
        return Cipher.getInstance(Constants.Security.AES_ALG).run {
            init(
                Cipher.ENCRYPT_MODE,
                key
            )
            val encrypted = doFinal(data.toByteArray())
            val vector = iv
            "${encodeBase64(encrypted)}${ENCRYPTION_DELIMITER}${encodeBase64(vector)}"
        }
    }

    fun decryptData(alias: String, data: String): String {
        val split = data.split(ENCRYPTION_DELIMITER)
        val encryptedData = decodeBase64(split.first())
        val vector = decodeBase64(split.second())
        val key = keystore.getKey(alias, null) ?: throw IllegalStateException("Missing key")
        val spec = GCMParameterSpec(128, vector)
        return Cipher.getInstance(Constants.Security.AES_ALG).run {
            init(
                Cipher.DECRYPT_MODE,
                key,
                spec
            )
            String(doFinal(encryptedData))
        }
    }

    fun hashMessage(message: ByteArray): String =
        MessageDigest.getInstance(Constants.Security.HASH_ALG).run {
            update(message)
            encodeBase64(digest())
        }

    fun signAndEncodeDataBase64(data: ByteArray): String {
        val entry = keystore.getKey(Constants.Security.DEVICE_USER_KEY, null) as PrivateKey
        return Signature.getInstance(Constants.Security.DEVICE_USER_KEY_SIG_ALG)
            .run {
                initSign(entry)
                update(data)
                encodeBase64(sign())
            }
    }

    fun encodeBase64(data: ByteArray) = BaseEncoding.base64().encode(data)
    fun decodeBase64(data: String) = BaseEncoding.base64().decode(data)
}
