package com.msg.data.remote.datasource.self_study

import com.msg.data.remote.network.SelfStudyApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class SelfStudyDataSourceImpl @Inject constructor(
    private val selfStudyApi: SelfStudyApi
): SelfStudyDataSource {
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
}