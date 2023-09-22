package com.msg.domain.usecase.notice

import com.msg.domain.repository.NoticeRepository
import javax.inject.Inject

class DeleteNoticeByIdListUseCase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(
        role: String,
        body: List<Long>
    ) = kotlin.runCatching {
        repository.deleteNoticeByIdList(
            role = role,
            body = body
        )
    }
}
