package app.vut.secnote.domain.tutorial

import app.vut.secnote.data.store.TutorialStore
import com.thefuntasty.mvvm.crinteractors.BaseCoroutineInteractor
import javax.inject.Inject

class GetEncryptionTutorialInteractor @Inject constructor(
    private val tutorialStore: TutorialStore
) : BaseCoroutineInteractor<Boolean>() {
    override suspend fun build(): Boolean = tutorialStore.encryptionTutorialState()
}
