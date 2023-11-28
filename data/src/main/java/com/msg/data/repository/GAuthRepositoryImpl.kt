package com.msg.data.repository

import com.msg.data.remote.datasource.auth.GAuthDataSource
import com.msg.data.remote.dto.auth.GAuthLoginRequest
import com.msg.data.remote.dto.auth.toLoginModel
import com.msg.domain.model.auth.GAuthLoginRequestModel
import com.msg.domain.model.auth.GAuthLoginResponseModel
import com.msg.domain.repository.GAuthRepository
import javax.inject.Inject

class GAuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: GAuthDataSource
): GAuthRepository {
    override suspend fun gAuthLogin(body: GAuthLoginRequestModel): GAuthLoginResponseModel =
        remoteDataSource.gAuthLogin(body = GAuthLoginRequest(code = body.code)).toLoginModel()
}