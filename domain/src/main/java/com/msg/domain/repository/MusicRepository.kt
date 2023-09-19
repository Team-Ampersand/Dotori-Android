package com.msg.domain.repository

import com.msg.domain.model.music.request.MusicRequestModel
import com.msg.domain.model.music.response.MusicResponseModel
import com.msg.domain.model.music.response.YoutubeResponseModel

interface MusicRepository {
    suspend fun getMusics(role: String, date: String): List<MusicResponseModel>

    suspend fun requestMusic(role: String, body: MusicRequestModel)

    suspend fun deleteMusic(role: String, musicId: Long)

    suspend fun getYoutubeMusic(youtubeUrl: String): YoutubeResponseModel
}
