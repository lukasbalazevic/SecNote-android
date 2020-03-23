package app.vut.secnote.domain.security

import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class GetKeyAliasesInteractor @Inject constructor(
    private val cryptoHelper: CryptoHelper
) : BaseCoroutineInteractor<List<String>>() {
    override suspend fun build(): List<String> = cryptoHelper.getKeystoreAliases()
}
