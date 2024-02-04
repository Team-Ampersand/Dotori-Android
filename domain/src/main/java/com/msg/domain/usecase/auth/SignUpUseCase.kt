package com.msg.domain.usecase.auth

import com.msg.domain.model.auth.SignUpRequestModel
import com.msg.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(body: SignUpRequestModel) = kotlin.runCatching { repository.signUp(body) }
}