package com.msg.data.remote.dto.auth

import com.msg.domain.model.auth.GAuthLoginResponseModel

data class GAuthLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)

fun GAuthLoginResponse.toLoginModel() =
    GAuthLoginResponseModel (
        accessToken = accessToken,
        refreshToken = refreshToken,
        accessTokenExp = accessTokenExp,
        refreshTokenExp = refreshTokenExp
    )