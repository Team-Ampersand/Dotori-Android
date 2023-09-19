package com.msg.data.remote.datasource.massage

import com.msg.data.remote.dto.massage.MassageInfoResponse
import com.msg.data.remote.dto.massage.MassageListResponse

interface MassageDataSource {
    suspend fun getMessageInfo(role: String): MassageInfoResponse

    suspend fun getMassageRank(role: String): List<MassageListResponse>

    suspend fun requestMassage(role: String)

    suspend fun cancelMassage(role: String)

    suspend fun changeMassageLimit(
        role: String,
        limit: Int
    )
}