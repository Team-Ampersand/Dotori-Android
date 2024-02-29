package com.msg.data.remote.dto.auth

import com.msg.domain.model.auth.ChangePasswordRequestModel

data class ChangePasswordRequest(
    val email: String,
    val newPassword: String
)

fun ChangePasswordRequestModel.asChangePasswordRequest() = ChangePasswordRequest(
    email = email,
    newPassword = newPassword
)