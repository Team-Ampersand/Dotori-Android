package com.msg.data.remote.dto.auth

import com.msg.domain.model.auth.LoginRequestModel

data class LoginRequest(
    val email: String,
    val password: String
)

fun LoginRequestModel.asLoginRequest() = LoginRequest(
    email = email,
    password = password
)