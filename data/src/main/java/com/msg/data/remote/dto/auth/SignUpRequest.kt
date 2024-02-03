package com.msg.data.remote.dto.auth

import com.msg.domain.model.auth.SignUpRequestModel

data class SignUpRequest(
    val memberName: String,
    val stuNum: String,
    val password: String,
    val email: String,
    val gender: Gender
)

fun SignUpRequestModel.asSignUpRequest() = SignUpRequest(
    memberName = memberName,
    stuNum = stuNum,
    password = password,
    email = email,
    gender = gender.asRole()
)