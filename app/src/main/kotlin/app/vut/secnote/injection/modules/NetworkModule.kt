package app.vut.secnote.injection.modules

import android.content.Context
import app.vut.secnote.authservice.AuthServiceGrpcKt
import app.vut.secnote.injection.ApplicationContext
import app.vut.secnote.permissionservice.PermissionServiceGrpcKt
import app.vut.secnote.tools.Constants
import dagger.Module
import dagger.Provides
import io.grpc.Channel
import io.grpc.android.AndroidChannelBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideChannel(@ApplicationContext context: Context): Channel = AndroidChannelBuilder
        .forTarget(Constants.Network.URL)
        .context(context)
        .idleTimeout(Constants.Network.IDLE_TIMEOUT, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideAuthStub(channel: Channel): AuthServiceGrpcKt.AuthServiceCoroutineStub =
        AuthServiceGrpcKt.AuthServiceCoroutineStub(channel)

    @Provides
    @Singleton
    fun providePermissionStub(channel: Channel): PermissionServiceGrpcKt.PermissionServiceCoroutineStub =
        PermissionServiceGrpcKt.PermissionServiceCoroutineStub(channel)
}
