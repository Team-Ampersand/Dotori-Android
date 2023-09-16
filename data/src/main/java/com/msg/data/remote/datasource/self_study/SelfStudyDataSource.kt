package com.msg.data.remote.datasource.self_study

interface SelfStudyDataSource {
    suspend fun banSelfStudy(
        role: String,
        userId: Long
    )

    suspend fun banCancelSelfStudy(
        role: String,
        userId: Long
    )
}