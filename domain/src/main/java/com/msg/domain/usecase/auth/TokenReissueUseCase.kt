package com.msg.domain.usecase.auth

import com.msg.domain.repository.AuthRepository
import javax.inject.Inject

class TokenReissueUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { authRepository.tokenReissue() }
}