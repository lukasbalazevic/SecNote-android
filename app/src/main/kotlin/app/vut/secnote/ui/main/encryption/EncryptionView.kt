package app.vut.secnote.ui.main.encryption

import com.thefuntasty.mvvm.BaseView

interface EncryptionView : BaseView {
    fun onKeySelected(alias: String)
}
