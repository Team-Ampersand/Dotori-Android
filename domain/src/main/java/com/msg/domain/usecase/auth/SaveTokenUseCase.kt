package com.msg.domain.usecase.auth

import com.msg.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        accessToken: String,
        refreshToken: String,
        expiresAt: String
    ) = kotlin.runCatching {
        authRepository.saveToken(
            accessToken,
            refreshToken,
            expiresAt
        )
    }
}