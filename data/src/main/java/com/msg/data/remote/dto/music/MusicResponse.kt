package com.msg.data.remote.dto.music

data class MusicResponse(
    val id: Long,
    val url: String,
    val username: String,
    val email: String,
    val createdTime: String,
    val stuNum: String
)