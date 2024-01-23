package com.msg.data.remote.dto.auth

import com.msg.domain.model.auth.LoginResponseModel

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: String,
    val roles: List<Role>
)

fun LoginResponse.asLoginResponseModel() = LoginResponseModel(
    accessToken = accessToken,
    refreshToken = refreshToken,
    expiresAt = expiresAt,
    roles = roles.map { it.asRoleModel() }
)