package com.msg.domain.repository

interface SelfStudyRepository {
    suspend fun banSelfStudy(
        role: String,
        userId: Long
    )

    suspend fun banCancelSelfStudy(
        role: String,
        userId: Long
    )
}