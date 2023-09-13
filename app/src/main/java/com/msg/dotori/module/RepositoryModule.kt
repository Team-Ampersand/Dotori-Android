package com.msg.dotori.module

import com.msg.data.repository.AuthRepositoryImpl
import com.msg.data.repository.MusicRepositoryImpl
import com.msg.domain.repository.AuthRepository
import com.msg.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindsMusicRepository(musicRepositoryImpl: MusicRepositoryImpl): MusicRepository
}