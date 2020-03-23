package app.vut.secnote.domain.security

import app.vut.secnote.data.model.ui.KeySize
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class CreateKeyInteractor @Inject constructor(
    private val cryptoHelper: CryptoHelper
) : BaseCoroutineInteractor<Unit>() {

    private lateinit var size: KeySize
    private lateinit var alias: String

    fun init(size: KeySize, alias: String) = apply {
        this.size = size
        this.alias = alias
    }

    override suspend fun build() = cryptoHelper.generateEncryptionKey(alias, size.size)
}
