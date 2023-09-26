package com.msg.data.remote.datasource.notice

import com.msg.data.remote.dto.notice.request.NoticeRequest
import com.msg.data.remote.dto.notice.response.NoticeResponse
import com.msg.data.remote.network.NoticeApi
import com.msg.data.remote.util.safeApiCall
import okhttp3.MultipartBody
import javax.inject.Inject

class NoticeDataSourceImpl @Inject constructor(
    private val noticeApi: NoticeApi
) : NoticeDataSource {
    override suspend fun writeNotice(
        role: String,
        files: List<MultipartBody.Part>,
        noticeRequest: NoticeRequest
    ) = safeApiCall {
        noticeApi.writeNotice(role, files, noticeRequest)
    }

    override suspend fun modifyNotice(
        role: String,
        noticeId: Long,
        body: NoticeRequest
    ) = safeApiCall {
        noticeApi.modifyNotice(role, noticeId, body)
    }

    override suspend fun deleteNoticeById(
        role: String,
        noticeId: Long
    ) = safeApiCall {
        noticeApi.deleteNoticeById(role, noticeId)
    }

    override suspend fun deleteNoticeByIdList(
        role: String,
        body: List<Long>
    ) = safeApiCall {
        noticeApi.deleteNoticeByIdList(role, body)
    }

    override suspend fun getAllNotice(role: String): List<NoticeResponse> = safeApiCall {
        noticeApi.getAllNotice(role)
    }

    override suspend fun getNoticeDetail(
        role: String,
        noticeId: Long
    ) = safeApiCall {
        noticeApi.getNoticeDetail(role, noticeId)
    }
}
