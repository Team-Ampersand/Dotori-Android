package com.msg.data.repository

import com.msg.data.remote.datasource.massage.MassageDataSource
import com.msg.data.remote.dto.massage.asMassageInfoResponseModel
import com.msg.data.remote.dto.massage.asMassageListResponseModel
import com.msg.domain.model.massage.MassageInfoResponseModel
import com.msg.domain.model.massage.MassageListResponseModel
import com.msg.domain.repository.MassageRepository
import javax.inject.Inject

class MassageRepositoryImpl @Inject constructor(
    private val massageDataSource: MassageDataSource
): MassageRepository {
    override suspend fun getMassageInfo(role: String): MassageInfoResponseModel =
        massageDataSource.getMassageInfo(role = role).asMassageInfoResponseModel()

    override suspend fun getMassageRank(role: String): List<MassageListResponseModel> =
        massageDataSource.getMassageRank(role = role).map { it.asMassageListResponseModel() }

    override suspend fun requestMassage(role: String) =
        massageDataSource.requestMassage(role = role)

    override suspend fun cancelMassage(role: String) =
        massageDataSource.cancelMassage(role = role)

    override suspend fun changeMassageLimit(role: String, limit: Int) =
        massageDataSource.changeMassageLimit(
            role = role,
            limit = limit
        )
}