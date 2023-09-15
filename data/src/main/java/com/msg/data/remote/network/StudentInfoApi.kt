package com.msg.data.remote.network

import com.msg.data.remote.dto.student_info.SearchStudentInfoResponse
import com.msg.data.remote.dto.student_info.SelfStudyStatus
import com.msg.data.remote.dto.student_info.StudentInfoRequest
import com.msg.data.remote.dto.student_info.StudentInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface StudentInfoApi {
    @GET("/student-info")
    suspend fun getAllStudentInfo(): List<StudentInfoResponse>

    @GET("/student-info/search")
    suspend fun getSearchStudentInfo(
        @Query("name") name: String,
        @Query("gender") gender: String,
        @Query("classNum") classNum: String,
        @Query("grade") grade: Long,
        @Query("role") role: String,
        @Query("selfStudy") selfStudy: SelfStudyStatus,
    ): List<SearchStudentInfoResponse>

    @PUT("/student-info/modify")
    suspend fun modifyStudentInfo(
        @Body body: StudentInfoRequest
    )
}