package com.msg.data.remote.datasource.auth

import com.msg.data.remote.dto.auth.GAuthLoginRequest
import com.msg.data.remote.dto.auth.GAuthLoginResponse

interface GAuthDataSource {
    suspend fun gAuthLogin(body: GAuthLoginRequest): GAuthLoginResponse
}