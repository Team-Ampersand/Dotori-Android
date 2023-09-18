package com.msg.domain.usecase.self_study

import com.msg.domain.repository.SelfStudyRepository
import javax.inject.Inject

class SearchSelfStudyStudentUseCase @Inject constructor(
    private val selfStudyRepository: SelfStudyRepository
) {
    suspend operator fun invoke(
        role: String,
        name: String?,
        gender: String?,
        classNum: String?,
        grade: Int?
    ) = kotlin.runCatching {
        selfStudyRepository.searchSelfStudyStudent(
            role = role,
            name = name,
            gender = gender,
            classNum = classNum,
            grade = grade

        )
    }
}