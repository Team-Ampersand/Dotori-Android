package com.msg.domain.usecase.massage

import com.msg.domain.repository.MassageRepository
import javax.inject.Inject

class ChangeMassageLimitUseCase @Inject constructor(
    private val massageRepository: MassageRepository
) {
    suspend operator fun invoke(
        role: String,
        limit: Int
    ) = kotlin.runCatching {
        massageRepository.changeMassageLimit(
            role = role,
            limit = limit
        )
    }
}