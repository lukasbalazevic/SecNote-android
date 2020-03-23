package app.vut.secnote.ui.main.encryption.create

import android.widget.RadioGroup
import app.vut.secnote.R
import app.vut.secnote.data.model.ui.KeySize
import app.vut.secnote.domain.security.CreateKeyInteractor
import com.thefuntasty.mvvm.crinteractors.BaseCrViewModel
import kotlinx.android.synthetic.main.fragment_create_key.view.*
import javax.inject.Inject

class CreateKeyViewModel @Inject constructor(
    override val viewState: CreateKeyViewState,
    private val createKeyInteractor: CreateKeyInteractor
) : BaseCrViewModel<CreateKeyViewState>() {

    fun createKey() {
        if (viewState.alias.value.isBlank()) {
            return
        }
        createKeyInteractor.init(viewState.size.value, viewState.alias.value).execute {
            sendEvent(NavigateBackEvent)
        }
    }

    fun navigateBack() {
        sendEvent(NavigateBackEvent)
    }

    fun onCheckedChange(radioGroup: RadioGroup, id: Int) {
        when(id) {
            R.id.encryption_create_aes128 -> viewState.size.value = KeySize.AES128
            R.id.encryption_create_aes192 -> viewState.size.value = KeySize.AES192
            R.id.encryption_create_aes256 -> viewState.size.value = KeySize.AES256
        }
    }
}

