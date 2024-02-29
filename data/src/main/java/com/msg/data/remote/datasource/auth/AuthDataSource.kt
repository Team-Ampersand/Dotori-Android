package com.msg.data.remote.datasource.auth

import com.msg.data.remote.dto.auth.ChangePasswordRequest
import com.msg.data.remote.dto.auth.LoginRequest
import com.msg.data.remote.dto.auth.LoginResponse
import com.msg.data.remote.dto.auth.SignUpRequest

interface AuthDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun tokenReissue(): LoginResponse

    suspend fun signUp(body: SignUpRequest)

    suspend fun changePassword(body: ChangePasswordRequest)
}