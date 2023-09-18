package com.msg.domain.usecase.self_study

import com.msg.domain.repository.SelfStudyRepository
import javax.inject.Inject

class GetSelfStudyListUseCase @Inject constructor(
    private val selfStudyRepository: SelfStudyRepository
) {
    suspend operator fun invoke(role: String) = kotlin.runCatching {
        selfStudyRepository.getSelfStudyList(role = role)
    }
}