package com.msg.domain.usecase.auth

import com.msg.domain.model.auth.GAuthLoginRequestModel
import com.msg.domain.repository.GAuthRepository
import javax.inject.Inject

class GAuthLoginUseCase @Inject constructor(
    private val gauthRepository: GAuthRepository
) {
    suspend operator fun invoke(body: GAuthLoginRequestModel) = kotlin.runCatching {
        gauthRepository.gAuthLogin(body = body)
    }
}
