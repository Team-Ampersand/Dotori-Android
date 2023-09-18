package com.msg.domain.usecase.self_study

import com.msg.domain.repository.SelfStudyRepository
import javax.inject.Inject

class GetSelfStudyInfoUseCase @Inject constructor(
    private val selfStudyRepository: SelfStudyRepository
) {
    suspend operator fun invoke(role: String) = kotlin.runCatching {
        selfStudyRepository.getSelfStudyInfo(role = role)
    }
}