package com.msg.data.remote.network

import com.msg.data.remote.dto.massage.MassageInfoResponse
import com.msg.data.remote.dto.massage.MassageListResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MassageApi {
    @GET("{role}/massage")
    suspend fun getMassageInfo(
        @Path("role") role: String
    ): MassageInfoResponse

    @GET("{role}/massage/rank")
    suspend fun getMassageRank(
        @Path("role") role: String
    ): List<MassageListResponse>

    @POST("{role}/massage")
    suspend fun requestMassage(
        @Path("role") role: String
    ): ResponseBody

    @DELETE("{role}/massage")
    suspend fun cancelMassage(
        @Path("role") role: String
    ): ResponseBody

    @PATCH("{role}/massage/limit")
    suspend fun changeMassageLimit(
        @Path("role") role: String,
        @Body limit: Int
    )
}