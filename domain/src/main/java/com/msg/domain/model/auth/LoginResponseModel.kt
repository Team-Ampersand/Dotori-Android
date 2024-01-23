package com.msg.domain.model.auth

data class LoginResponseModel(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: String,
    val roles: List<RoleModel>
)