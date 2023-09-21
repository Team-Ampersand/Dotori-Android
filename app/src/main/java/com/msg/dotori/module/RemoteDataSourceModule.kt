package com.msg.dotori.module

import com.msg.data.remote.datasource.auth.AuthDataSourceImpl
import com.msg.data.remote.datasource.auth.AuthDataSource
import com.msg.data.remote.datasource.massage.MassageDataSource
import com.msg.data.remote.datasource.massage.MassageDataSourceImpl
import com.msg.data.remote.datasource.music.MusicDataSource
import com.msg.data.remote.datasource.music.MusicDataSourceImpl
import com.msg.data.remote.datasource.self_study.SelfStudyDataSource
import com.msg.data.remote.datasource.self_study.SelfStudyDataSourceImpl
import com.msg.data.remote.datasource.student_info.StudentInfoDataSource
import com.msg.data.remote.datasource.student_info.StudentInfoDataSourceImpl
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

    @Binds
    fun bindsStudentInfoDataSource(studentInfoDataSourceImpl: StudentInfoDataSourceImpl): StudentInfoDataSource

    @Binds
    fun bindsSelfStudyDataSource(selfStudyDataSourceImpl: SelfStudyDataSourceImpl): SelfStudyDataSource

    @Binds
    fun bindsMassageDataSource(massageDataSourceImpl: MassageDataSourceImpl): MassageDataSource
}