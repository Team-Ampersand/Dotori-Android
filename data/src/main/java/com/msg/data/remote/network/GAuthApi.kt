package com.msg.data.remote.network

import com.msg.data.remote.dto.auth.GAuthLoginRequest
import com.msg.data.remote.dto.auth.GAuthLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface GAuthApi {
    @POST("auth")
    suspend fun gAuthLogin(
        @Body body: GAuthLoginRequest
    ): GAuthLoginResponse
}