package com.msg.domain.usecase.music

import com.msg.domain.repository.MusicRepository
import javax.inject.Inject

class GetMusicsUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(role: String, date: String) = kotlin.runCatching { repository.getMusics(role, date) }
}