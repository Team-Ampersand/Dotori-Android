package com.msg.data.repository

import com.msg.data.remote.datasource.self_study.SelfStudyDataSource
import com.msg.data.remote.dto.self_study.asSelfStudyInfoResponseModel
import com.msg.data.remote.dto.self_study.asSelfStudyListResponseModel
import com.msg.domain.model.self_study.SelfStudyInfoResponseModel
import com.msg.domain.model.self_study.SelfStudyListResponseModel
import com.msg.domain.repository.SelfStudyRepository
import okhttp3.ResponseBody
import javax.inject.Inject

class SelfStudyRepositoryImpl @Inject constructor(
    private val selfStudyDataSource: SelfStudyDataSource
): SelfStudyRepository {
    override suspend fun getSelfStudyInfo(role: String): SelfStudyInfoResponseModel =
        selfStudyDataSource.getSelfStudyInfo(role = role).asSelfStudyInfoResponseModel()

    override suspend fun getSelfStudyList(role: String): List<SelfStudyListResponseModel> =
        selfStudyDataSource.getSelfStudyList(role = role).map { it.asSelfStudyListResponseModel() }

    override suspend fun searchSelfStudyStudent(
        role: String,
        name: String?,
        gender: String?,
        classNum: String?,
        grade: Int?
    ): List<SelfStudyListResponseModel> = selfStudyDataSource.searchSelfStudyStudent(
        role = role,
        name = name,
        gender = gender,
        classNum = classNum,
        grade = grade
    ).map { it.asSelfStudyListResponseModel() }

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

    override suspend fun selfStudy(role: String): ResponseBody =
        selfStudyDataSource.selfStudy(role)

    override suspend fun cancelSelfStudy(role: String): ResponseBody =
        selfStudyDataSource.cancelSelfStudy(role)

    override suspend fun changeSelfStudyLimit(role: String, limit: Int) =
        selfStudyDataSource.changeSelfStudyLimit(
            role = role,
            limit = limit
        )

    override suspend fun checkSelfStudy(role: String, memberId: Long, selfStudyCheck: Boolean) =
        selfStudyDataSource.checkSelfStudy(
            role = role,
            memberId = memberId,
            selfStudyCheck = selfStudyCheck
        )
}