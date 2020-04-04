package app.vut.secnote.data.store

import android.content.SharedPreferences
import app.vut.secnote.tools.Constants
import javax.inject.Inject

class TutorialStore @Inject constructor(
    private val persistence: SharedPreferences
) {
    fun encryptionTutorialState() = persistence.getBoolean(Constants.Note.ENCRYPTION_TUTORIAL, false)
    fun markEncryptionTutorialAsSeen() = persistence.edit().putBoolean(Constants.Note.ENCRYPTION_TUTORIAL, true).commit()
}
