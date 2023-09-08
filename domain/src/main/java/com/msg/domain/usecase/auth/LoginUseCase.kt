package com.msg.domain.usecase.auth

import com.msg.domain.model.auth.LoginRequestModel
import com.msg.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginRequestModel: LoginRequestModel) = kotlin.runCatching { authRepository.login(loginRequestModel) }
}