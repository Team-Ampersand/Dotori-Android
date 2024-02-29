package com.msg.domain.usecase.email

import com.msg.domain.model.email.SendEmailRequestModel
import com.msg.domain.repository.EmailRepository
import javax.inject.Inject

class SendSignUpEmailUseCase @Inject constructor(
    private val repository: EmailRepository
) {
    suspend operator fun invoke(body: SendEmailRequestModel) = kotlin.runCatching { repository.sendSignUpEmail(body) }
}