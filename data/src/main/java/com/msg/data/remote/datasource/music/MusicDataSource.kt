package com.msg.data.remote.datasource.music

import com.msg.data.remote.dto.music.MusicRequest
import com.msg.data.remote.dto.music.MusicResponse

interface MusicDataSource {
    suspend fun getMusics(role: String, date: String): List<MusicResponse>

    suspend fun requestMusic(role: String, body: MusicRequest)

    suspend fun deleteMusic(role: String, musicId: Long)
}