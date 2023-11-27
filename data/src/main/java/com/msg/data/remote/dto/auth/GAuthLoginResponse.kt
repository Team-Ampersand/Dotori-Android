package com.msg.data.remote.dto.auth

data class GAuthLoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)