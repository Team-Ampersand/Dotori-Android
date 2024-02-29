package com.msg.domain.usecase.auth

import com.msg.domain.model.auth.ChangePasswordRequestModel
import com.msg.domain.repository.AuthRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(body: ChangePasswordRequestModel) = kotlin.runCatching { repository.changePassword(body) }
}