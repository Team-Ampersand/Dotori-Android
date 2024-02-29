package com.msg.data.remote.datasource.auth

import com.msg.data.remote.dto.auth.ChangePasswordRequest
import com.msg.data.remote.dto.auth.LoginRequest
import com.msg.data.remote.dto.auth.LoginResponse
import com.msg.data.remote.dto.auth.SignUpRequest
import com.msg.data.remote.network.AuthApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authApi: AuthApi
): AuthDataSource {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse = safeApiCall {
        authApi.login(loginRequest)
    }

    override suspend fun tokenReissue(): LoginResponse = safeApiCall {
        authApi.tokenReissue()
    }

    override suspend fun signUp(body: SignUpRequest) = safeApiCall {
        authApi.signUp(body)
    }

    override suspend fun changePassword(body: ChangePasswordRequest) = safeApiCall {
        authApi.changePassword(body)
    }
}