package app.vut.secnote.injection.modules

import android.app.KeyguardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.security.keystore.KeyProperties
import androidx.biometric.BiometricPrompt
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import app.vut.secnote.App
import app.vut.secnote.R
import app.vut.secnote.data.database.ApplicationDatabase
import app.vut.secnote.injection.ApplicationContext
import app.vut.secnote.injection.SharedPrefKey
import app.vut.secnote.tools.Constants
import dagger.Module
import dagger.Provides
import java.security.KeyPairGenerator
import java.security.KeyStore
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    fun context(app: App): Context = app

    @Singleton
    @Provides
    fun resources(@ApplicationContext context: Context): Resources = context.resources

    @Singleton
    @Provides
    fun keystore(): KeyStore = KeyStore.getInstance(Constants.Security.KEYSTORE).apply { load(null) }

    @Singleton
    @Provides
    fun keyPairGenerator(): KeyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, Constants.Security.KEYSTORE)

    @Singleton
    @Provides
    @SharedPrefKey
    fun providePersistenceKey() = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    @Singleton
    @Provides
    fun sharedPreferences(@ApplicationContext context: Context, @SharedPrefKey masterKeyAlias: String): SharedPreferences =
        EncryptedSharedPreferences.create(
            Constants.Security.ENCRYPTED_SHARED_PREFS,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Singleton
    @Provides
    fun applicationDatabase(@ApplicationContext context: Context): ApplicationDatabase {
        return Room.databaseBuilder(context, ApplicationDatabase::class.java, Constants.Database.NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun keyguardManager(@ApplicationContext context: Context): KeyguardManager =
        context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

    @Provides
    fun biometricPromptInfo(resources: Resources) = BiometricPrompt.PromptInfo.Builder()
        .setTitle(resources.getString(R.string.general_lock_screen_title))
        .setSubtitle(resources.getString(R.string.general_lock_screen_subtitle))
        .setDeviceCredentialAllowed(true)
        .build()
}
