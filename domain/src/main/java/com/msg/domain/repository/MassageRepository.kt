package com.msg.domain.repository

import com.msg.domain.model.massage.MassageInfoResponseModel
import com.msg.domain.model.massage.MassageListResponseModel
import okhttp3.ResponseBody

interface MassageRepository {
    suspend fun getMassageInfo(role: String): MassageInfoResponseModel

    suspend fun getMassageRank(role: String): List<MassageListResponseModel>

    suspend fun requestMassage(role: String): ResponseBody

    suspend fun cancelMassage(role: String): ResponseBody

    suspend fun changeMassageLimit(
        role: String,
        limit: Int
    )
}