package com.msg.domain.usecase.self_study

import com.msg.domain.repository.SelfStudyRepository
import javax.inject.Inject

class BanCancelSelfStudyUseCase @Inject constructor(
    private val selfStudyRepository: SelfStudyRepository
) {
    suspend operator fun invoke(
        role: String,
        userId: Long
    ) = kotlin.runCatching {
        selfStudyRepository.banCancelSelfStudy(
            role = role,
            userId = userId
        )
    }
}