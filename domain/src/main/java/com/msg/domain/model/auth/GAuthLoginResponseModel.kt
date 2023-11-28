package com.msg.domain.model.auth

data class GAuthLoginResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExp: String,
    val refreshTokenExp: String
)
