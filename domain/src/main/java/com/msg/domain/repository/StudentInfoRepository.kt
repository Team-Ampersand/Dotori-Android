package com.msg.domain.repository

import com.msg.domain.model.student_info.SearchStudentInfoResponseModel
import com.msg.domain.model.student_info.StudentInfoRequestModel
import com.msg.domain.model.student_info.StudentInfoResponseModel

interface StudentInfoRepository {
    suspend fun getAllStudentInfo(): List<StudentInfoResponseModel>

    suspend fun getSearchStudentInfo(
        name: String?,
        gender: String?,
        classNum: String?,
        grade: String?,
        role: String?,
        selfStudy: Boolean?,
    ): List<SearchStudentInfoResponseModel>

    suspend fun modifyStudentInfo(body: StudentInfoRequestModel)
}