package app.vut.secnote.injection.modules

import app.vut.secnote.authservice.AuthServiceCoroutineGrpc
import dagger.Module
import dagger.Provides
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideChannel(): Channel = ManagedChannelBuilder
        .forTarget("grpc.secnote.space")
        .build()

    @Provides
    @Singleton
    fun provideAuthStub(channel: Channel): AuthServiceCoroutineGrpc.AuthServiceCoroutineStub
        = AuthServiceCoroutineGrpc.newStub(channel)
}
