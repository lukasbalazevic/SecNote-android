package app.vut.secnote.data.store

import android.content.SharedPreferences
import app.vut.secnote.tools.Constants
import javax.inject.Inject

class TokenStore @Inject constructor(private val persistence: SharedPreferences) {
    fun getAccessToken() = persistence.getString(Constants.Security.ACCESS_TOKEN, null)
    fun getRefreshToken() = persistence.getString(Constants.Security.REFRESH_TOKEN, null)
    fun saveAccessToken(token: String) = persistence.edit().putString(Constants.Security.ACCESS_TOKEN, token).commit()
    fun saveRefreshToken(token: String) = persistence.edit().putString(Constants.Security.REFRESH_TOKEN, token).commit()
    fun deleteTokens() = persistence.edit()
        .remove(Constants.Security.REFRESH_TOKEN)
        .remove(Constants.Security.ACCESS_TOKEN)
        .commit()
}