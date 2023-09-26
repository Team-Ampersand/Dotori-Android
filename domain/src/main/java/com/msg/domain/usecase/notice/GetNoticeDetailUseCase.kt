package com.msg.domain.usecase.notice

import com.msg.domain.repository.NoticeRepository
import javax.inject.Inject

class GetNoticeDetailUseCase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(
        role: String,
        noticeId: Long
    ) = kotlin.runCatching {
        repository.getNoticeDetail(
            role = role,
            noticeId = noticeId
        )
    }
}
