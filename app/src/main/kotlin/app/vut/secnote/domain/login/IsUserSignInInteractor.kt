package app.vut.secnote.domain.login

import app.vut.secnote.data.store.TokenStore
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class IsUserSignInInteractor @Inject constructor(
    private val tokenStore: TokenStore
) : BaseCoroutineInteractor<Boolean>() {
    override suspend fun build(): Boolean = tokenStore.getAccessToken() != null
}