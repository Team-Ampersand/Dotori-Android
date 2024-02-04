package com.msg.data.remote.network

import com.msg.data.remote.dto.email.EmailVerifyRequest
import com.msg.data.remote.dto.email.SendEmailRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailApi {
    @POST("email/signup")
    suspend fun sendEmail(
        @Body body: SendEmailRequest
    )

    @POST("email/verify-email")
    suspend fun emailVerify(
        @Body body: EmailVerifyRequest
    )
}