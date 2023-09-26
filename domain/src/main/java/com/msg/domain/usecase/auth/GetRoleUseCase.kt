package com.msg.domain.usecase.auth

import com.msg.domain.repository.AuthRepository
import javax.inject.Inject

class GetRoleUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = kotlin.runCatching { repository.getRole() }
}
