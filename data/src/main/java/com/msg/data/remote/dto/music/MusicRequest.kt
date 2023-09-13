package com.msg.data.remote.dto.music

import com.msg.domain.model.music.request.MusicRequestModel

data class MusicRequest(
    val url: String
)

fun MusicRequestModel.asMusicRequest() = MusicRequest(
    url = url
)