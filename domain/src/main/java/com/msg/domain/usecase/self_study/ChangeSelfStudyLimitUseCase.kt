package com.msg.domain.usecase.self_study

import com.msg.domain.repository.SelfStudyRepository
import javax.inject.Inject

class ChangeSelfStudyLimitUseCase @Inject constructor(
    private val selfStudyRepository: SelfStudyRepository
) {
    suspend operator fun invoke(
        role: String,
        limit: Int
    ) = kotlin.runCatching {
        selfStudyRepository.changeSelfStudyLimit(
            role = role,
            limit = limit
        )
    }
}