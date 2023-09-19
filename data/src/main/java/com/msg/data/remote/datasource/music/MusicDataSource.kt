package com.msg.data.remote.datasource.music

import com.msg.data.remote.dto.music.request.MusicRequest
import com.msg.data.remote.dto.music.response.MusicResponse
import com.msg.data.remote.dto.music.response.YoutubeResponse

interface MusicDataSource {
    suspend fun getMusics(role: String, date: String): List<MusicResponse>

    suspend fun requestMusic(role: String, body: MusicRequest)

    suspend fun deleteMusic(role: String, musicId: Long)

    suspend fun getYoutubeMusic(youtubeUrl: String): YoutubeResponse
}
