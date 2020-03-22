package app.vut.secnote.data.store

import android.content.SharedPreferences
import app.vut.secnote.tools.Constants
import javax.inject.Inject

class UserStore @Inject constructor(private val persistence: SharedPreferences) {
    fun getUserEmail() = persistence.getString(Constants.User.EMAIL, "") ?: ""
    fun saveUserEmail(email: String) = persistence.edit().putString(Constants.User.EMAIL, email).commit()
}