package com.msg.data.remote.dto.music

import com.msg.domain.model.music.response.MusicResponseModel

data class MusicResponse(
    val id: Long,
    val url: String,
    val username: String,
    val email: String,
    val createdTime: String,
    val stuNum: String
)

fun MusicResponse.asMusicResponseModel() = MusicResponseModel(
    id = id,
    url = url,
    username = username,
    email = email,
    createdTime = createdTime,
    stuNum = stuNum
)