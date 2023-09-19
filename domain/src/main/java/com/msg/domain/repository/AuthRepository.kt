package com.msg.domain.repository

import com.msg.domain.model.auth.LoginRequestModel
import com.msg.domain.model.auth.LoginResponseModel

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequestModel): LoginResponseModel

    suspend fun tokenReissue(): LoginResponseModel

    suspend fun saveToken(
        accessToken: String,
        refreshToken: String,
        expiresAt: String
    )

    suspend fun saveRole(roles: String)

    suspend fun getRole(): String
}
