package com.msg.domain.repository

import com.msg.domain.model.auth.GAuthLoginRequestModel
import com.msg.domain.model.auth.GAuthLoginResponseModel

interface GAuthRepository {
    suspend fun gAuthLogin(body: GAuthLoginRequestModel): GAuthLoginResponseModel
}