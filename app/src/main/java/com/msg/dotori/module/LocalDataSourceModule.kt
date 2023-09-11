package com.msg.dotori.module

import com.msg.data.local.datasource.LocalDataSource
import com.msg.data.local.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {
    @Binds
    fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}