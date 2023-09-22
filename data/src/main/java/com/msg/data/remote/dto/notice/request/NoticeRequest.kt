package com.msg.data.remote.dto.notice.request

import com.google.gson.annotations.SerializedName

data class NoticeRequest(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String
)
