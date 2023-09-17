package com.msg.domain.usecase.student_info

import com.msg.domain.model.student_info.StudentInfoRequestModel
import com.msg.domain.repository.StudentInfoRepository
import javax.inject.Inject

class ModifyStudentInfoUseCase @Inject constructor(
    private val studentInfoRepository: StudentInfoRepository
) {
    suspend operator fun invoke(body: StudentInfoRequestModel) = kotlin.runCatching { studentInfoRepository.modifyStudentInfo(body) }
}