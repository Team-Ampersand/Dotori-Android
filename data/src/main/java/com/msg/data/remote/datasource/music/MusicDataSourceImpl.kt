package com.msg.data.remote.datasource.music

import com.msg.data.remote.dto.music.request.MusicRequest
import com.msg.data.remote.dto.music.response.MusicResponse
import com.msg.data.remote.dto.music.response.YoutubeResponse
import com.msg.data.remote.network.MusicApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class MusicDataSourceImpl @Inject constructor(
    private val musicApi: MusicApi
): MusicDataSource {
    override suspend fun getMusics(role: String, date: String): List<MusicResponse> = safeApiCall {
        musicApi.getMusics(role, date)
    }

    override suspend fun requestMusic(role: String, body: MusicRequest) = safeApiCall {
        musicApi.requestMusic(role, body)
    }

    override suspend fun deleteMusic(role: String, musicId: Long) = safeApiCall {
        musicApi.deleteMusic(role, musicId)
    }

    override suspend fun getYoutubeMusic(youtubeUrl: String): YoutubeResponse = safeApiCall {
        musicApi.getYoutubeMusic(youtubeUrl = youtubeUrl)
    }
}
