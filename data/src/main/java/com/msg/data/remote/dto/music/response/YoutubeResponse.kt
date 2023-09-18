package com.msg.data.remote.dto.music.response

import com.google.gson.annotations.SerializedName

data class YoutubeResponse(
    val title: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String
)