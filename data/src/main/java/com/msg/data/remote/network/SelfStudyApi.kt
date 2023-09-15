package com.msg.data.remote.network

import retrofit2.http.PUT
import retrofit2.http.Path

interface SelfStudyApi {
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
}
