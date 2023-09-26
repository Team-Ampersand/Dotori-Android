package com.msg.data.remote.dto.notice.request

import com.google.gson.annotations.SerializedName
import com.msg.domain.model.notice.request.NoticeRequestModel

data class NoticeRequest(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)

fun NoticeRequestModel.asNoticeRequest() = NoticeRequest(
    title = title,
    content = content
)
