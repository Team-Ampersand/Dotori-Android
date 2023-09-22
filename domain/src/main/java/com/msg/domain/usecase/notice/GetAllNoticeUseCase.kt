package com.msg.domain.usecase.notice

import com.msg.domain.repository.NoticeRepository
import javax.inject.Inject

class GetAllNoticeUseCase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(role: String) = kotlin.runCatching { repository.getAllNotice(role = role) }
}
