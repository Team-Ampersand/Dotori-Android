package com.msg.data.remote.datasource.email

import com.msg.data.remote.dto.email.EmailVerifyRequest
import com.msg.data.remote.dto.email.SendEmailRequest

interface EmailDataSource {
    suspend fun sendEmail(body: SendEmailRequest)

    suspend fun emailVerify(body: EmailVerifyRequest)
}