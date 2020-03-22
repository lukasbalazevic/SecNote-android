package app.vut.secnote.domain.login

import app.vut.secnote.data.remote.AuthServiceManager
import app.vut.secnote.data.store.TokenStore
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class SignOutInteractor @Inject constructor(
    private val tokenStore: TokenStore,
    private val authServiceManager: AuthServiceManager
) : BaseCoroutineInteractor<Unit>() {

    private var includeServerCall = true

    fun init(includeServerCall: Boolean = true) = apply {
        this.includeServerCall = includeServerCall
    }

    override suspend fun build() {
        if (includeServerCall) {
            authServiceManager.signOut()
        }
        tokenStore.deleteTokens()
    }
}