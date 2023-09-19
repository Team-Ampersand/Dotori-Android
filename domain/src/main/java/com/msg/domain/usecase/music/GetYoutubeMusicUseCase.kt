package com.msg.domain.usecase.music

import com.msg.domain.repository.MusicRepository
import javax.inject.Inject

class GetYoutubeMusicUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(youtubeUrl: String) = kotlin.runCatching { repository.getYoutubeMusic(youtubeUrl) }
}
