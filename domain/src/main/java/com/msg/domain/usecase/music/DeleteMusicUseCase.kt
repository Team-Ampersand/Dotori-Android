package com.msg.domain.usecase.music

import com.msg.domain.repository.MusicRepository
import javax.inject.Inject

class DeleteMusicUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(role: String, musicId: Long) = kotlin.runCatching { repository.deleteMusic(role, musicId) }
}