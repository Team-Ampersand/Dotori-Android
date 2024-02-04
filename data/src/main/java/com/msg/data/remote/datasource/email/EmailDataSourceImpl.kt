package com.msg.data.remote.datasource.email

import com.msg.data.remote.dto.email.EmailVerifyRequest
import com.msg.data.remote.dto.email.SendEmailRequest
import com.msg.data.remote.network.EmailApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class EmailDataSourceImpl @Inject constructor(
    private val emailApi: EmailApi
): EmailDataSource {
    override suspend fun sendEmail(body: SendEmailRequest) = safeApiCall {
        emailApi.sendEmail(body)
    }

    override suspend fun emailVerify(body: EmailVerifyRequest) = safeApiCall {
        emailApi.emailVerify(body)
    }
}