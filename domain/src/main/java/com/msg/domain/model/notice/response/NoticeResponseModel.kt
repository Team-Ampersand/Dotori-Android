package com.msg.domain.model.notice.response

import java.time.LocalDateTime

data class NoticeResponseModel(
    val id: Long,
    val title: String,
    val content: String,
    val role: String,
    val createdDate: LocalDateTime
)
