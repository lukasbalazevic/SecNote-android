package app.vut.secnote.injection.modules

import android.content.Context
import app.vut.secnote.authservice.AuthServiceGrpcKt
import app.vut.secnote.injection.ApplicationContext
import app.vut.secnote.permissionservice.PermissionServiceGrpcKt
import dagger.Module
import dagger.Provides
import io.grpc.android.AndroidChannelBuilder
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideAuthStub(@ApplicationContext context: Context): AuthServiceGrpcKt.AuthServiceCoroutineStub = AuthServiceGrpcKt.AuthServiceCoroutineStub(
        AndroidChannelBuilder
            .forTarget("grpc.secnote.space")
            .context(context)
            .intercept()
            .build()
    )

    @Provides
    @Singleton
    fun providePermissionStub(@ApplicationContext context: Context): PermissionServiceGrpcKt.PermissionServiceCoroutineStub = PermissionServiceGrpcKt.PermissionServiceCoroutineStub(
        AndroidChannelBuilder
            .forTarget("grpc.secnote.space")
            .context(context)
            .build()
    )
}
