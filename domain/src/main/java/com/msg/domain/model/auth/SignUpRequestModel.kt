package com.msg.domain.model.auth

data class SignUpRequestModel(
    val memberName: String,
    val stuNum: String,
    val password: String,
    val email: String,
    val gender: GenderModel
)
