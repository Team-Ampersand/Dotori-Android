package com.msg.data.remote.datasource.self_study

import com.msg.data.remote.dto.self_study.SelfStudyInfoResponse
import com.msg.data.remote.dto.self_study.SelfStudyListResponse

interface SelfStudyDataSource {
    suspend fun getSelfStudyInfo(
        role: String
    ): SelfStudyInfoResponse

    suspend fun getSelfStudyList(
        role: String
    ): List<SelfStudyListResponse>

    suspend fun searchSelfStudyStudent(
        role: String,
        name: String?,
        gender: String?,
        classNum: String?,
        grade: Int?
    ): List<SelfStudyListResponse>

    suspend fun banSelfStudy(
        role: String,
        userId: Long
    )

    suspend fun banCancelSelfStudy(
        role: String,
        userId: Long
    )

    suspend fun selfStudy(role: String)

    suspend fun cancelSelfStudy(role: String)

    suspend fun changeSelfStudyLimit(
        role: String,
        limit: Int
    )

    suspend fun checkSelfStudy(
        role: String,
        memberId: String,
        selfStudyCheck: Boolean
    )
}