package com.msg.data.remote.dto.email

import com.msg.domain.model.email.SendEmailRequestModel

data class SendEmailRequest(
    val email: String
)

fun SendEmailRequestModel.asSendEmailRequest() = SendEmailRequest(
    email = email
)