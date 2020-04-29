package app.vut.secnote.ui.main.profile

import app.vut.secnote.domain.login.SignOutInteractor
import app.vut.secnote.domain.user.GetUserInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    override val viewState: ProfileViewState,
    private val signOutInteractor: SignOutInteractor,
    private val getUserInteractor: GetUserInteractor
) : BaseCrViewModel<ProfileViewState>() {

    override fun onStart() {
        getUserInteractor.execute(onSuccess = {
            viewState.email.value = it
        })
    }

    fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }

    fun signOut() {
        signOutInteractor.execute(
            onSuccess = {
                sendEvent(NavigateToLoginEvent)
            },
            onError = {
                sendEvent(ErrorOccurredEvent)
            })
    }
}
