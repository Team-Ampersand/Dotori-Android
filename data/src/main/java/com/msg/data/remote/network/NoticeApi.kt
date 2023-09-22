package com.msg.data.remote.network

import com.msg.data.remote.dto.notice.request.NoticeRequest
import com.msg.data.remote.dto.notice.response.NoticeResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface NoticeApi {
    @Multipart
    @POST("/{role}/board")
    suspend fun writeNotice(
        @Path("role") role: String,
        @Part files: List<MultipartBody.Part>,
        @Part("boardDto") noticeRequest: NoticeRequest
    )

    @PUT("/{role}/board/{board_id}")
    suspend fun modifyNotice(
        @Path("role") role: String,
        @Path("board_id") noticeId: Long,
        @Body body: NoticeRequest
    )

    @DELETE("/{role}/board/{board_id}")
    suspend fun deleteNoticeById(
        @Path("role") role: String,
        @Path("board_id") noticeId: Long,
    )

    @HTTP(method = "DELETE", path = "/{role}/board", hasBody = true)
    suspend fun deleteNoticeByIdList(
        @Path("role") role: String,
        @Body body: List<Long>
    )

    @GET("/{role}/board")
    suspend fun getAllNotice(
        @Path("role") role: String,
    ): List<NoticeResponse>

    @GET("/{role}/board/{board_id}")
    suspend fun getNoticeDetail(
        @Path("role") role: String,
        @Path("board_id") noticeId: Long
    )
}
