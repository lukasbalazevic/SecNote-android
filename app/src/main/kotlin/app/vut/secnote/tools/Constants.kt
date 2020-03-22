package app.vut.secnote.tools

interface Constants {

    object Security {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"

        const val KEYSTORE = "AndroidKeyStore"
        const val DEVICE_USER_KEY = "DEVICE_USER_KEY"
        const val DEVICE_USER_KEY_SIG_ALG = "SHA512withRSA"
        const val DEVICE_USER_KEY_SIG_SIZE = 2048
        const val HASH_ALG = "SHA-512"
        const val SALT = "@Secnote?2020?Thesis!@"
        const val ENCRYPTED_SHARED_PREFS = "secret_shared_prefs"
        const val DEVICE_AUTHORIZATION_WINDOW = 60 * 20
    }

    object Database {
        const val VERSION = 1
        const val NAME = "secnote.db"
        const val CATEGORY_SEPARATOR = ";;"
    }

    object User {
        const val EMAIL = "EMAIL"
    }

    object Note {
        const val NOTE_BODY_PREVIEW_LENGTH = 60
    }
}
