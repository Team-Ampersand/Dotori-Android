package com.msg.domain.repository

import com.msg.domain.model.notice.request.NoticeRequestModel
import com.msg.domain.model.notice.response.NoticeDetailResponseModel
import com.msg.domain.model.notice.response.NoticeResponseModel
import okhttp3.MultipartBody

interface NoticeRepository {
    suspend fun writeNotice(
        role: String,
        files: List<MultipartBody.Part>,
        noticeRequest: NoticeRequestModel
    )

    suspend fun modifyNotice(
        role: String,
        noticeId: Long,
        body: NoticeRequestModel
    )

    suspend fun deleteNoticeById(
        role: String,
        noticeId: Long
    )

    suspend fun deleteNoticeByIdList(
        role: String,
        body: List<Long>
    )

    suspend fun getAllNotice(role: String): List<NoticeResponseModel>

    suspend fun getNoticeDetail(
        role: String,
        noticeId: Long
    ): NoticeDetailResponseModel
}
