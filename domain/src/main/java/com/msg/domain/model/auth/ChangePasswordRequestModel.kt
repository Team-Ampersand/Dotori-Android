package com.msg.domain.model.auth

data class ChangePasswordRequestModel(
    val email: String,
    val newPassword: String
)