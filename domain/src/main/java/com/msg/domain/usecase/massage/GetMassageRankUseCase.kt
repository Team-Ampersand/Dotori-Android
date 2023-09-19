package com.msg.domain.usecase.massage

import com.msg.domain.repository.MassageRepository
import javax.inject.Inject

class GetMassageRankUseCase @Inject constructor(
    private val massageRepository: MassageRepository
) {
    suspend operator fun invoke(role: String) = kotlin.runCatching {
        massageRepository.getMassageInfo(role = role)
    }
}