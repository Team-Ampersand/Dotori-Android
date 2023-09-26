package com.msg.data.remote.datasource.notice

import com.msg.data.remote.dto.notice.request.NoticeRequest
import com.msg.data.remote.dto.notice.response.NoticeDetailResponse
import com.msg.data.remote.dto.notice.response.NoticeResponse
import okhttp3.MultipartBody

interface NoticeDataSource {
    suspend fun writeNotice(
        role: String,
        files: List<MultipartBody.Part>,
        noticeRequest: NoticeRequest
    )

    suspend fun modifyNotice(
        role: String,
        noticeId: Long,
        body: NoticeRequest
    )

    suspend fun deleteNoticeById(
        role: String,
        noticeId: Long,
    )

    suspend fun deleteNoticeByIdList(
        role: String,
        body: List<Long>
    )

    suspend fun getAllNotice(
        role: String,
    ): List<NoticeResponse>

    suspend fun getNoticeDetail(
        role: String,
        noticeId: Long
    ): NoticeDetailResponse
}
