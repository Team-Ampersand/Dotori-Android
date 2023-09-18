package com.msg.domain.repository

import com.msg.domain.model.self_study.SelfStudyInfoResponseModel
import com.msg.domain.model.self_study.SelfStudyListResponseModel

interface SelfStudyRepository {
    suspend fun getSelfStudyInfo(
        role: String
    ): SelfStudyInfoResponseModel

    suspend fun getSelfStudyList(
        role: String
    ): List<SelfStudyListResponseModel>

    suspend fun searchSelfStudyStudent(
        role: String,
        name: String?,
        gender: String?,
        classNum: String?,
        grade: Int?
    ): List<SelfStudyListResponseModel>

    suspend fun banSelfStudy(
        role: String,
        userId: Long
    )

    suspend fun banCancelSelfStudy(
        role: String,
        userId: Long
    )

    suspend fun selfStudy(
        role: String
    )

    suspend fun cancelSelfStudy(
        role: String
    )

    suspend fun changeSelfStudyLimit(
        role: String,
        limit: Int
    )

    suspend fun checkSelfStudy(
        role: String,
        memberId: Long,
        selfStudyCheck: Boolean
    )
}