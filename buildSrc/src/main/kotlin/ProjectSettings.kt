object ProjectSettings {

    const val projectName = "SecNote"
    const val applicationId = "app.vut.secnote"
    const val compileSdkVersion = 29
    const val targetSdk = 29
    const val minSdk = 23
    const val versionName = "1.0.0"
    val versionCode = System.getenv("BITRISE_BUILD_NUMBER")?.toInt() ?: 1

    object Flavor {
        const val DIMENSION = "api"

        const val MOCK = "mock"
        const val DEV = "dev"
        const val PROD = "prod"
    }

    object BuildType {
        const val DEBUG = "debug"
        const val ENTERPRISE = "enterprise"
        const val RELEASE = "release"
    }

    object Debug {
        const val KEY_ALIAS = "androiddebugkey"
        const val KEY_PASSWORD = "android"
        const val STORE_PASSWORD = "android"
    }

    object Release {
        const val KEY_ALIAS = "TBD"
        val KEY_PASSWORD = System.getenv("KEY_PASSWORD") ?: ""
        val STORE_PASSWORD = System.getenv("STORE_PASSWORD") ?: ""
    }

    const val TASK_GROUP = "futured"
}
