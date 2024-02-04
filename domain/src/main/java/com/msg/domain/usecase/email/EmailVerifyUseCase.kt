package com.msg.domain.usecase.email

import com.msg.domain.model.email.EmailVerifyRequestModel
import com.msg.domain.repository.EmailRepository
import javax.inject.Inject

class EmailVerifyUseCase @Inject constructor(
    private val repository: EmailRepository
) {
    suspend operator fun invoke(body: EmailVerifyRequestModel) = kotlin.runCatching { repository.emailVerify(body) }
}