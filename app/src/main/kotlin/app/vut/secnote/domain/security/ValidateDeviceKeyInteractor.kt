package app.vut.secnote.domain.security

import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class ValidateDeviceKeyInteractor @Inject constructor(
    private val cryptoHelper: CryptoHelper
) : BaseCoroutineInteractor<Boolean>() {
    override suspend fun build() = cryptoHelper.checkIfDeviceKeyExists()
}