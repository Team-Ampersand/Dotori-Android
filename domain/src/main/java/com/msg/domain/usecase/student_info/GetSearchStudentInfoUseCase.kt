package com.msg.domain.usecase.student_info

import com.msg.domain.repository.StudentInfoRepository
import javax.inject.Inject

class GetSearchStudentInfoUseCase @Inject constructor(
    private val studentInfoRepository: StudentInfoRepository
) {
    suspend operator fun invoke(
        name: String?,
        gender: String?,
        classNum: String?,
        grade: String?,
        role: String?,
        selfStudy: Boolean?,
    ) = kotlin.runCatching { studentInfoRepository.getSearchStudentInfo(
            name = name,
            gender = gender,
            classNum = classNum,
            grade = grade,
            role = role,
            selfStudy = selfStudy
        )
    }
}