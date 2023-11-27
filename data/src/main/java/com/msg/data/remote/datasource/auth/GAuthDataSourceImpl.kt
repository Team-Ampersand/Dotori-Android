package com.msg.data.remote.datasource.auth

import com.msg.data.remote.dto.auth.GAuthLoginRequest
import com.msg.data.remote.dto.auth.GAuthLoginResponse
import com.msg.data.remote.network.GAuthApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class GAuthDataSourceImpl @Inject constructor(
    private val gauthApi: GAuthApi
): GAuthDataSource {
    override suspend fun gAuthLogin(body: GAuthLoginRequest): GAuthLoginResponse =
        safeApiCall { gauthApi.gAuthLogin(body = body) }
}