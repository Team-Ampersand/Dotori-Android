package com.msg.data.remote.datasource.massage

import com.msg.data.remote.dto.massage.MassageInfoResponse
import com.msg.data.remote.dto.massage.MassageListResponse
import okhttp3.ResponseBody

interface MassageDataSource {
    suspend fun getMassageInfo(role: String): MassageInfoResponse

    suspend fun getMassageRank(role: String): List<MassageListResponse>

    suspend fun requestMassage(role: String): ResponseBody

    suspend fun cancelMassage(role: String): ResponseBody

    suspend fun changeMassageLimit(
        role: String,
        limit: Int
    )
}