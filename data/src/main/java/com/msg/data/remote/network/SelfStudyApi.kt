package com.msg.data.remote.network

import com.msg.data.remote.dto.self_study.SelfStudyInfoResponse
import com.msg.data.remote.dto.self_study.SelfStudyListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SelfStudyApi {
    @GET("/{role}/self-study/info")
    suspend fun getSelfStudyInfo(
        @Path("role") role: String
    ): SelfStudyInfoResponse

    @GET("/{role}/self-study/rank")
    suspend fun getSelfStudyList(
        @Path("role") role: String
    ): List<SelfStudyListResponse>

    @GET("/{role}/self-study/search")
    suspend fun searchSelfStudyStudent(
        @Path("role") role: String,
        @Query("name") name: String?,
        @Query("gender") gender: String?,
        @Query("classNum") classNum: String?,
        @Query("grade") grade: Int?
    ): List<SelfStudyListResponse>

    @PUT("/{role}/self-study/ban/{userId}")
    suspend fun banSelfStudy(
        @Path("role") role: String,
        @Path("userId") userId: Long
    )

    @PUT("/{role}/self-study/ban/cancel/{userId}")
    suspend fun banCancelSelfStudy(
        @Path("role") role: String,
        @Path("userId") userId: Long
    )

    @POST("/{role}/self-study")
    suspend fun selfStudy(
        @Path("role") role: String
    )

    @DELETE("/{role}/self-study")
    suspend fun cancelSelfStudy(
        @Path("role") role: String
    )

    @PATCH("/{role}/self-study/limit")
    suspend fun changeSelfStudyLimit(
        @Path("role") role: String,
        @Body limit: Int
    )

    @PATCH("/{role}/self-study/check/{memberId}")
    suspend fun checkSelfStudy(
        @Path("role") role: String,
        @Path("memberId") memberId: Long,
        @Body selfStudyCheck: Boolean
    )
}