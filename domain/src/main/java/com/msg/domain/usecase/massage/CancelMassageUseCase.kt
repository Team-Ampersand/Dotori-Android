package com.msg.domain.usecase.massage

import com.msg.domain.repository.MassageRepository
import javax.inject.Inject

class CancelMassageUseCase @Inject constructor(
    private val massageRepository: MassageRepository
) {
    suspend operator fun invoke(role: String) = kotlin.runCatching {
        massageRepository.cancelMassage(role = role)
    }
}