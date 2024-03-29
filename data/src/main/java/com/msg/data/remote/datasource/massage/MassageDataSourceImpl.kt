package com.msg.data.remote.datasource.massage

import com.msg.data.remote.dto.massage.MassageInfoResponse
import com.msg.data.remote.dto.massage.MassageListResponse
import com.msg.data.remote.network.MassageApi
import com.msg.data.remote.util.safeApiCall
import okhttp3.ResponseBody
import javax.inject.Inject

class MassageDataSourceImpl @Inject constructor(
    private val massageApi: MassageApi
): MassageDataSource {
    override suspend fun getMassageInfo(role: String): MassageInfoResponse = safeApiCall {
        massageApi.getMassageInfo(role = role)
    }

    override suspend fun getMassageRank(role: String): List<MassageListResponse> = safeApiCall {
        massageApi.getMassageRank(role = role)
    }

    override suspend fun requestMassage(role: String): ResponseBody = safeApiCall {
        massageApi.requestMassage(role = role)
    }

    override suspend fun cancelMassage(role: String): ResponseBody = safeApiCall {
        massageApi.cancelMassage(role = role)
    }

    override suspend fun changeMassageLimit(role: String, limit: Int) = safeApiCall {
        massageApi.changeMassageLimit(
            role = role,
            limit = limit
        )
    }
}