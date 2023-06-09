package com.bayarsahintekin.matchscores.di

import com.bayarsahintekin.data.utils.DiskExecutor
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import com.bayarsahintekin.matchscores.util.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider {
        return DispatchersProviderImpl
    }

}