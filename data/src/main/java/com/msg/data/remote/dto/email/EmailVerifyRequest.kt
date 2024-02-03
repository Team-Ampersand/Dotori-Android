package com.msg.data.remote.dto.email

import com.msg.domain.model.email.EmailVerifyRequestModel

data class EmailVerifyRequest(
    val key: String
)

fun EmailVerifyRequestModel.asEmailVerifyRequest() = EmailVerifyRequest(
    key = key
)