package com.msg.data.remote.network

import com.msg.data.remote.dto.student_info.SearchStudentInfoResponse
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
        @Query("name") name: String? = null,
        @Query("gender") gender: String? = null,
        @Query("classNum") classNum: String? = null,
        @Query("grade") grade: String? = null,
        @Query("role") role: String? = null,
        @Query("selfStudy") selfStudy: Boolean? = null,
    ): List<SearchStudentInfoResponse>

    @PUT("/student-info/modify")
    suspend fun modifyStudentInfo(
        @Body body: StudentInfoRequest
    )
}