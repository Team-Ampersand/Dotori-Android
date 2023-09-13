package com.msg.dotori.module

import com.msg.data.remote.datasource.auth.AuthDataSourceImpl
import com.msg.data.remote.datasource.auth.AuthDataSource
import com.msg.data.remote.datasource.music.MusicDataSource
import com.msg.data.remote.datasource.music.MusicDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    fun bindsAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    fun bindsMusicDataSource(musicDataSourceImpl: MusicDataSourceImpl): MusicDataSource
}