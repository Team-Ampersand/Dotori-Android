package com.msg.domain.usecase.self_study

import com.msg.domain.repository.SelfStudyRepository
import javax.inject.Inject

class CheckSelfStudyUseCase @Inject constructor(
    private val selfStudyRepository: SelfStudyRepository
) {
    suspend operator fun invoke(
        role: String,
        memberId: Long,
        selfStudyCheck: Boolean
    ) = kotlin.runCatching {
        selfStudyRepository.checkSelfStudy(
            role = role,
            memberId = memberId,
            selfStudyCheck = selfStudyCheck
        )
    }
}