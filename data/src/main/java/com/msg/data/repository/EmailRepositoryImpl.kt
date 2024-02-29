package com.msg.data.repository

import com.msg.data.remote.datasource.email.EmailDataSource
import com.msg.data.remote.dto.email.asEmailVerifyRequest
import com.msg.data.remote.dto.email.asSendEmailRequest
import com.msg.domain.model.email.EmailVerifyRequestModel
import com.msg.domain.model.email.SendEmailRequestModel
import com.msg.domain.repository.EmailRepository
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val emailDataSource: EmailDataSource
): EmailRepository {
    override suspend fun sendSignUpEmail(body: SendEmailRequestModel) = emailDataSource.sendSignUpEmail(body.asSendEmailRequest())

    override suspend fun sendPasswordEmail(body: SendEmailRequestModel) = emailDataSource.sendPasswordEmail(body.asSendEmailRequest())

    override suspend fun emailVerify(body: EmailVerifyRequestModel) = emailDataSource.emailVerify(body.asEmailVerifyRequest())
}