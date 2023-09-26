package com.msg.domain.usecase.notice

import com.msg.domain.model.notice.request.NoticeRequestModel
import com.msg.domain.repository.NoticeRepository
import javax.inject.Inject

class ModifyNoticeUseCase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(
        role: String,
        noticeId: Long,
        body: NoticeRequestModel
    ) = kotlin.runCatching {
        repository.modifyNotice(
            role = role,
            noticeId = noticeId,
            body = body
        )
    }
}
