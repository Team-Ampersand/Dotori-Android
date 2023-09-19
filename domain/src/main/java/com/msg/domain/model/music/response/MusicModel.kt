package com.msg.domain.model.music.response

data class MusicModel(
    val id: Long,
    val url: String,
    val username: String,
    val email: String,
    val createdTime: String,
    val stuNum: String,
    val title: String,
    val thumbnailUrl: String
)
