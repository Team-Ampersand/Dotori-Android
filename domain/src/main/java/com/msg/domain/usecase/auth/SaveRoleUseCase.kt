package com.msg.domain.usecase.auth

import com.msg.domain.repository.AuthRepository
import javax.inject.Inject

class SaveRoleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(roles: String) = kotlin.runCatching { authRepository.saveRole(roles) }
}