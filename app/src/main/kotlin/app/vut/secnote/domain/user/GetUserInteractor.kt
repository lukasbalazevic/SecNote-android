package app.vut.secnote.domain.user

import app.vut.secnote.data.store.UserStore
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class GetUserInteractor @Inject constructor(
    private val userStore: UserStore
) : BaseCoroutineInteractor<String>() {
    override suspend fun build(): String = userStore.getUserEmail()
}