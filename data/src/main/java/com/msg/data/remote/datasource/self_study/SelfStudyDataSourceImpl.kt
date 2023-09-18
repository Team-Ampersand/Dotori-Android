package com.msg.data.remote.datasource.self_study

import com.msg.data.remote.dto.self_study.SelfStudyInfoResponse
import com.msg.data.remote.dto.self_study.SelfStudyListResponse
import com.msg.data.remote.network.SelfStudyApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class SelfStudyDataSourceImpl @Inject constructor(
    private val selfStudyApi: SelfStudyApi
): SelfStudyDataSource {
    override suspend fun selfStudyInfo(role: String): SelfStudyInfoResponse = safeApiCall {
        selfStudyApi.selfStudyInfo(role = role)
    }

    override suspend fun selfStudyList(role: String): List<SelfStudyListResponse> = safeApiCall {
        selfStudyApi.selfStudyList(role = role)
    }

    override suspend fun searchSelfStudyStudent(
        role: String,
        name: String?,
        gender: String?,
        classNum: String?,
        grade: Int?
    ): List<SelfStudyListResponse> = safeApiCall {
        selfStudyApi.searchSelfStudyStudent(
            role = role,
            name = name,
            gender = gender,
            classNum = classNum,
            grade = grade
        )
    }

    override suspend fun banSelfStudy(role: String, userId: Long) = safeApiCall {
        selfStudyApi.banSelfStudy(
            role = role,
            userId = userId
        )
    }

    override suspend fun banCancelSelfStudy(role: String, userId: Long) = safeApiCall {
        selfStudyApi.banCancelSelfStudy(
            role = role,
            userId = userId
        )
    }

    override suspend fun selfStudy(role: String) = safeApiCall {
        selfStudyApi.selfStudy(role = role)
    }

    override suspend fun cancelSelfStudy(role: String) = safeApiCall {
        selfStudyApi.cancelSelfStudy(role = role)
    }

    override suspend fun changeSelfStudyLimit(role: String, limit: Int) = safeApiCall {
        selfStudyApi.changeSelfStudyLimit(
            role = role,
            limit = limit
        )
    }

    override suspend fun checkSelfStudy(role: String, memberId: String, selfStudyCheck: Boolean) = safeApiCall {
        selfStudyApi.checkSelfStudy(
            role = role,
            memberId = memberId,
            selfStudyCheck = selfStudyCheck
        )
    }
}