package com.msg.data.remote.datasource.student_info

import com.msg.data.remote.dto.student_info.SearchStudentInfoResponse
import com.msg.data.remote.dto.student_info.StudentInfoRequest
import com.msg.data.remote.dto.student_info.StudentInfoResponse
import com.msg.data.remote.network.StudentInfoApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class StudentInfoDataSourceImpl @Inject constructor(
    private val studentInfoApi: StudentInfoApi
): StudentInfoDataSource {
    override suspend fun getAllStudentInfo(): List<StudentInfoResponse> = safeApiCall {
        studentInfoApi.getAllStudentInfo()
    }

    override suspend fun getSearchStudentInfo(
        name: String?,
        gender: String?,
        classNum: String?,
        grade: String?,
        role: String?,
        selfStudy: Boolean?,
    ): List<SearchStudentInfoResponse> = safeApiCall {
        studentInfoApi.getSearchStudentInfo(
            name = name,
            gender = gender,
            classNum = classNum,
            grade = grade,
            role = role,
            selfStudy = selfStudy
        )
    }

    override suspend fun modifyStudentInfo(body: StudentInfoRequest) = safeApiCall {
        studentInfoApi.modifyStudentInfo(body)
    }
}