package com.msg.domain.usecase.music

import com.msg.domain.model.music.request.MusicRequestModel
import com.msg.domain.repository.MusicRepository
import javax.inject.Inject

class RequestMusicUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(role: String, body: MusicRequestModel) = kotlin.runCatching { repository.requestMusic(role, body) }
}