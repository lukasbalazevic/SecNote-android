package app.vut.secnote.domain.login

import app.vut.secnote.authservice.CredentialsResponse
import app.vut.secnote.data.remote.AuthServiceManager
import app.vut.secnote.data.store.TokenStore
import app.vut.secnote.domain.security.CryptoHelper
import app.vut.secnote.tools.Constants
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class SignUpInteractor @Inject constructor(
    private val authServiceManager: AuthServiceManager,
    private val cryptoHelper: CryptoHelper,
    private val tokenStore: TokenStore
) : BaseCoroutineInteractor<CredentialsResponse>() {

    private lateinit var email: String
    private lateinit var password: String

    fun init(email: String, password: String) = apply {
        this.email = email
        this.password = password
    }

    override suspend fun build(): CredentialsResponse {

        val key = if (cryptoHelper.checkIfDeviceKeyExists()) {
            cryptoHelper.getDeviceKey()
        } else {
            cryptoHelper.generateKey()
        }
        val hashedPassword = cryptoHelper.hashMessage("$password${Constants.Security.SALT}".toByteArray())
        return authServiceManager.signUp(email, hashedPassword, key)

    /*    tokenStore.saveAccessToken(creds.jwt.accessToken)
        tokenStore.saveRefreshToken(creds.jwt.refreshToken)

        return creds*/
    }
}