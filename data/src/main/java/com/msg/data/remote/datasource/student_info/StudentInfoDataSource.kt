package com.msg.data.remote.datasource.student_info

import com.msg.data.remote.dto.student_info.SearchStudentInfoResponse
import com.msg.data.remote.dto.student_info.StudentInfoRequest
import com.msg.data.remote.dto.student_info.StudentInfoResponse

interface StudentInfoDataSource {
    suspend fun getAllStudentInfo(): List<StudentInfoResponse>

    suspend fun getSearchStudentInfo(
        name: String?,
        gender: String?,
        classNum: String?,
        grade: String?,
        role: String?,
        selfStudy: Boolean?,
    ): List<SearchStudentInfoResponse>

    suspend fun modifyStudentInfo(body: StudentInfoRequest)
}