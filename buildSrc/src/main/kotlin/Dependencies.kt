object Dependencies {
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    object Plugins {
        const val detekt = "io.gitlab.arturbosch.detekt"
        const val ktlint = "org.jlleitschuh.gradle.ktlint"
        const val templates = "com.thefuntasty.mvvm.android-templates"

        const val grpc = "grpc"
        const val java = "javalite"
        const val coroutines = "coroutines"
    }

    object Kotlin {
        const val gradlePlugin = "gradle-plugin"
        const val stdlib = "stdlib-jdk7"
    }

    object Support {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val ktx = "androidx.core:core-ktx:${Versions.androidx}"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val vectordrawable = "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawable}"
        const val preference = "androidx.preference:preference-ktx:${Versions.preference}"
        const val security = "androidx.security:security-crypto:${Versions.security}"
    }

    object DependencyInjection {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object NavigationComponents {
        const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationComponents}"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponents}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponents}"
    }

    object Grpc {
        const val protobuf = "com.google.protobuf:protoc:${Versions.protobuf}"
        const val genJava = "com.google.protobuf:protoc-gen-javalite:${Versions.java}"
        const val genGrpc = "io.grpc:protoc-gen-grpc-java:${Versions.grpc}"
        const val krotoPlusProtocGen = "com.github.marcoferrer.krotoplus:protoc-gen-grpc-coroutines:${Versions.krotoPlus}"

        const val javaxAnnotation = "javax.annotation:javax.annotation-api:${Versions.javaxAnnotation}"
        const val grpcStub = "io.grpc:grpc-stub:${Versions.grpc}"
        const val grpcOkHttp = "io.grpc:grpc-okhttp:${Versions.grpc}"
        const val grpcCore = "io.grpc:grpc-core:${Versions.grpc}"
        const val grpcProtoLite = "io.grpc:grpc-protobuf-lite:${Versions.grpc}"

        const val krotoPlus = "com.github.marcoferrer.krotoplus:kroto-plus-coroutines:${Versions.krotoPlus}"
    }

    object Taste {
        const val mvvmDagger = "com.thefuntasty.mvvm:dagger:${Versions.mvvm}"
        const val mvvmCrInteractors = "com.thefuntasty.mvvm:cr-interactors:${Versions.mvvm}"
    }

    object Other {
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val threetenbp = "org.threeten:threetenbp:${Versions.threetenbp}:"
    }

    object Test {
        const val core = "androidx.test:core:${Versions.testCore}"
        const val runner = "androidx.test:runner:${Versions.testRunner}"
        const val junit = "androidx.test.ext:junit:${Versions.junit}"
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
    }

    object Room {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    }
}
