package com.msg.data.remote.dto.notice.response

import com.google.gson.annotations.SerializedName
import com.msg.domain.model.notice.response.NoticeDetailResponseModel
import com.msg.domain.model.notice.response.NoticeImageModel
import java.time.LocalDateTime

data class NoticeDetailResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("role") val role: String,
    @SerializedName("boardImage") val noticeImages: List<NoticeImage>,
    @SerializedName("createdDate") val createdDate: LocalDateTime,
    @SerializedName("modifiedDate") val modifiedDate: LocalDateTime
)

data class NoticeImage(
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String
)

fun NoticeDetailResponse.asNoticeDetailResponseModel() = NoticeDetailResponseModel(
    id = id,
    title = title,
    content = content,
    role = role,
    noticeImages = noticeImages.map { it.asNoticeImageModel() },
    createdDate = createdDate,
    modifiedDate = modifiedDate
)

fun NoticeImage.asNoticeImageModel() = NoticeImageModel(
    id = id,
    url = url
)
