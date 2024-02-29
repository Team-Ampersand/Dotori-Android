package com.msg.domain.repository

import com.msg.domain.model.email.EmailVerifyRequestModel
import com.msg.domain.model.email.SendEmailRequestModel

interface EmailRepository {
    suspend fun sendSignUpEmail(body: SendEmailRequestModel)

    suspend fun sendPasswordEmail(body: SendEmailRequestModel)

    suspend fun emailVerify(body: EmailVerifyRequestModel)
}