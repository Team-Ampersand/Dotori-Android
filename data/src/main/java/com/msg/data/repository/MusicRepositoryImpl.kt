package com.msg.data.repository

import com.msg.data.remote.datasource.music.MusicDataSource
import com.msg.data.remote.dto.music.asMusicRequest
import com.msg.data.remote.dto.music.asMusicResponseModel
import com.msg.domain.model.music.request.MusicRequestModel
import com.msg.domain.model.music.response.MusicResponseModel
import com.msg.domain.repository.MusicRepository
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicDataSource: MusicDataSource
): MusicRepository {
    override suspend fun getMusics(role: String, date: String): List<MusicResponseModel> =
        musicDataSource.getMusics(role, date).map { it.asMusicResponseModel() }

    override suspend fun requestMusic(role: String, body: MusicRequestModel) =
        musicDataSource.requestMusic(role, body.asMusicRequest())

    override suspend fun deleteMusic(role: String, musicId: Long) =
        musicDataSource.deleteMusic(role, musicId)
}