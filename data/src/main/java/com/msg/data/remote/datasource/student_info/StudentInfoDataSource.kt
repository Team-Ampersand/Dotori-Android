package com.msg.data.remote.datasource.student_info

import com.msg.data.remote.dto.student_info.SearchStudentInfoResponse
import com.msg.data.remote.dto.student_info.SelfStudyStatus
import com.msg.data.remote.dto.student_info.StudentInfoRequest
import com.msg.data.remote.dto.student_info.StudentInfoResponse

interface StudentInfoDataSource {
    suspend fun getAllStudentInfo(): List<StudentInfoResponse>

    suspend fun getSearchStudentInfo(
        name: String,
        gender: String,
        classNum: String,
        grade: Long,
        role: String,
        selfStudy: SelfStudyStatus,
    ): List<SearchStudentInfoResponse>

    suspend fun modifyStudentInfo(body: StudentInfoRequest)
}