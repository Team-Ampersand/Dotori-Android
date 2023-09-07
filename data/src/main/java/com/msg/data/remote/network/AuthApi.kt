package com.msg.data.remote.network

import com.msg.data.remote.dto.auth.LoginRequest
import com.msg.data.remote.dto.auth.LoginResponse
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @PATCH("/auth")
    suspend fun tokenReissue(): LoginResponse
}