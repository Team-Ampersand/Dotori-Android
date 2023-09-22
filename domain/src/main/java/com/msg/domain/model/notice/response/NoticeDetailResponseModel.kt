package com.msg.domain.model.notice.response

import java.time.LocalDateTime

data class NoticeDetailResponseModel(
    val id: Long,
    val title: String,
    val content: String,
    val role: String,
    val noticeImages: List<NoticeImageModel>,
    val createdDate: LocalDateTime,
    val modifiedDate: LocalDateTime
)

data class NoticeImageModel(
    val id: Long,
    val url: String
)

