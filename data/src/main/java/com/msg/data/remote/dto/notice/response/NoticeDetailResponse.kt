package com.msg.data.remote.dto.notice.response

import com.google.gson.annotations.SerializedName
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
