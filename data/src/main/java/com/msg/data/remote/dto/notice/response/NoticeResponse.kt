package com.msg.data.remote.dto.notice.response

import java.time.LocalDateTime

data class NoticeResponse(
    val id: Long,
    val title: String,
    val content: String,
    val role: String,
    val createdDate: LocalDateTime
)
