package com.msg.data.remote.datasource.auth

import com.msg.data.remote.dto.auth.LoginRequest
import com.msg.data.remote.dto.auth.LoginResponse

interface AuthDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse

    suspend fun tokenReissue(): LoginResponse
}