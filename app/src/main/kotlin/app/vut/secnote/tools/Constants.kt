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
        const val AES_ALG = "AES/GCM/NoPadding"
        const val AES = "AES"
        const val SALT = "@Secnote?2020?Thesis!@"
        const val ENCRYPTED_SHARED_PREFS = "secret_shared_prefs"
        const val DEVICE_AUTHORIZATION_WINDOW = 60 * 1
    }

    object Database {
        const val VERSION = 2
        const val NAME = "secnote.db"
        const val CATEGORY_SEPARATOR = ";;"
    }

    object User {
        const val EMAIL = "EMAIL"
    }

    object Note {
        const val NOTE_BODY_PREVIEW_LENGTH = 60
        const val CATEGORIES_CHANGE = "CATEGORIES_CHANGE"
        const val KEY_SELECTED = "KEY_SELECTED"
        const val ENCRYPTION_TUTORIAL = "ENCRYPTION_TUTORIAL"
        const val DECRYPTION_TUTORIAL = "DECRYPTION_TUTORIAL"
    }

    object Bundle {
        const val DIALOG_TAG = "dialog_tag"
        const val DIALOG_TITLE = "dialog_title"
        const val DIALOG_BODY = "dialog_body"
        const val DIALOG_POSITIVE = "dialog_positive"
        const val DIALOG_NEGATIVE = "dialog_negative"
        const val DIALOG_NEUTRAL = "dialog_neutral"
        const val DIALOG_SINGLE_CHOICE_ARRAY_RES = "dialog_single_choice_array_res"
        const val DIALOG_SINGLE_CHOICE_PRESET_POSITION = "dialog_single_choice_preset_position"
    }
}
