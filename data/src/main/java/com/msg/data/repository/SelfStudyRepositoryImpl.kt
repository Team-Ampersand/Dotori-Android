package com.msg.data.repository

import com.msg.data.remote.datasource.self_study.SelfStudyDataSource
import com.msg.domain.repository.SelfStudyRepository
import javax.inject.Inject

class SelfStudyRepositoryImpl @Inject constructor(
    private val selfStudyDataSource: SelfStudyDataSource
): SelfStudyRepository {
    override suspend fun banSelfStudy(role: String, userId: Long) =
        selfStudyDataSource.banSelfStudy(
            role = role,
            userId = userId
        )

    override suspend fun banCancelSelfStudy(role: String, userId: Long) =
        selfStudyDataSource.banCancelSelfStudy(
            role = role,
            userId = userId
        )
}