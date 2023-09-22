package com.msg.data.repository

import com.msg.data.remote.datasource.notice.NoticeDataSource
import com.msg.data.remote.dto.notice.request.asNoticeRequest
import com.msg.data.remote.dto.notice.response.asNoticeResponseModel
import com.msg.domain.model.notice.request.NoticeRequestModel
import com.msg.domain.model.notice.response.NoticeResponseModel
import com.msg.domain.repository.NoticeRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val noticeDataSource: NoticeDataSource
): NoticeRepository {
    override suspend fun writeNotice(
        role: String,
        files: List<MultipartBody.Part>,
        noticeRequest: NoticeRequestModel
    ) = noticeDataSource.writeNotice(
        role,
        files,
        noticeRequest.asNoticeRequest()
    )

    override suspend fun modifyNotice(
        role: String,
        noticeId: Long,
        body: NoticeRequestModel
    ) = noticeDataSource.modifyNotice(
        role,
        noticeId,
        body.asNoticeRequest()
    )

    override suspend fun deleteNoticeById(
        role: String,
        noticeId: Long
    ) = noticeDataSource.deleteNoticeById(
        role,
        noticeId
    )

    override suspend fun deleteNoticeByIdList(
        role: String,
        body: List<Long>
    ) = noticeDataSource.deleteNoticeByIdList(
        role,
        body
    )

    override suspend fun getAllNotice(role: String): List<NoticeResponseModel> =
        noticeDataSource.getAllNotice(role).map { it.asNoticeResponseModel() }

    override suspend fun getNoticeDetail(
        role: String,
        noticeId: Long
    ) = noticeDataSource.getNoticeDetail(
        role,
        noticeId
    )
}
