package com.msg.data.repository

import com.msg.data.remote.datasource.student_info.StudentInfoDataSource
import com.msg.data.remote.dto.student_info.asSearchStudentInfoResponseModel
import com.msg.data.remote.dto.student_info.asStudentInfoRequest
import com.msg.data.remote.dto.student_info.asStudentInfoResponseModel
import com.msg.domain.model.student_info.SearchStudentInfoResponseModel
import com.msg.domain.model.student_info.StudentInfoRequestModel
import com.msg.domain.model.student_info.StudentInfoResponseModel
import com.msg.domain.repository.StudentInfoRepository
import javax.inject.Inject

class StudentInfoRepositoryImpl @Inject constructor(
    private val studentInfoDataSource: StudentInfoDataSource
): StudentInfoRepository {
    override suspend fun getAllStudentInfo(): List<StudentInfoResponseModel> =
        studentInfoDataSource.getAllStudentInfo().map { it.asStudentInfoResponseModel() }

    override suspend fun getSearchStudentInfo(
        name: String?,
        gender: String?,
        classNum: String?,
        grade: String?,
        role: String?,
        selfStudy: Boolean?
    ): List<SearchStudentInfoResponseModel> = studentInfoDataSource.getSearchStudentInfo(
        name =  name,
        gender = gender,
        classNum = classNum,
        grade = grade,
        role = role,
        selfStudy = selfStudy
    ).map { it.asSearchStudentInfoResponseModel() }

    override suspend fun modifyStudentInfo(body: StudentInfoRequestModel) =
        studentInfoDataSource.modifyStudentInfo(body.asStudentInfoRequest())
}