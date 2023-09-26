package com.msg.data.remote.dto.notice.response

import com.google.gson.annotations.SerializedName
import com.msg.domain.model.notice.response.NoticeResponseModel
import java.time.LocalDateTime

data class NoticeResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("role") val role: String,
    @SerializedName("createdDate") val createdDate: LocalDateTime
)

fun NoticeResponse.asNoticeResponseModel() = NoticeResponseModel(
    id = id,
    title = title,
    content = content,
    role = role,
    createdDate = createdDate
)
