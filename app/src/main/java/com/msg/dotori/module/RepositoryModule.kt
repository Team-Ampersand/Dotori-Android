package com.msg.dotori.module

import com.msg.data.repository.AuthRepositoryImpl
import com.msg.data.repository.EmailRepositoryImpl
import com.msg.data.repository.MassageRepositoryImpl
import com.msg.data.repository.MusicRepositoryImpl
import com.msg.data.repository.NoticeRepositoryImpl
import com.msg.data.repository.RuleViolationRepositoryImpl
import com.msg.data.repository.SelfStudyRepositoryImpl
import com.msg.data.repository.StudentInfoRepositoryImpl
import com.msg.domain.repository.AuthRepository
import com.msg.domain.repository.EmailRepository
import com.msg.domain.repository.MassageRepository
import com.msg.domain.repository.MusicRepository
import com.msg.domain.repository.NoticeRepository
import com.msg.domain.repository.RuleViolationRepository
import com.msg.domain.repository.SelfStudyRepository
import com.msg.domain.repository.StudentInfoRepository
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

    @Binds
    fun bindsSelfStudyRepository(selfStudyRepositoryImpl: SelfStudyRepositoryImpl): SelfStudyRepository

    @Binds
    fun bindsStudentInfoRepository(studentInfoRepositoryImpl: StudentInfoRepositoryImpl): StudentInfoRepository

    @Binds
    fun bindsMassageRepository(massageRepositoryImpl: MassageRepositoryImpl): MassageRepository

    @Binds
    fun bindsNoticeRepository(noticeRepositoryImpl: NoticeRepositoryImpl): NoticeRepository

    @Binds
    fun bindsRuleViolationRepository(ruleViolationRepositoryImpl: RuleViolationRepositoryImpl): RuleViolationRepository

    @Binds
    fun bindsEmailRepository(emailRepositoryImpl: EmailRepositoryImpl): EmailRepository
}
