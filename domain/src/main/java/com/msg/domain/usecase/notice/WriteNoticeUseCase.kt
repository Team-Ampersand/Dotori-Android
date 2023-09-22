package com.msg.domain.usecase.notice

import com.msg.domain.model.notice.request.NoticeRequestModel
import com.msg.domain.repository.NoticeRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class WriteNoticeUseCase @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend operator fun invoke(
        role: String,
        files: List<MultipartBody.Part>,
        noticeRequest: NoticeRequestModel
    ) = kotlin.runCatching {
        repository.writeNotice(
            role = role,
            files = files,
            noticeRequest = noticeRequest
        )
    }
}
