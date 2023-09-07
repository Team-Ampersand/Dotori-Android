package com.msg.data.remote.dto.auth

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: String,
    val roles: Role
)