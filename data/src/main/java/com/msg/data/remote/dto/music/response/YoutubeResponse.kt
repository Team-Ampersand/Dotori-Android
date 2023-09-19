package com.msg.data.remote.dto.music.response

import com.google.gson.annotations.SerializedName
import com.msg.domain.model.music.response.YoutubeResponseModel

data class YoutubeResponse(
    val title: String,
    @SerializedName("thumbnail_url") val thumbnailUrl: String
)

fun YoutubeResponse.asYoutubeResponseModel() = YoutubeResponseModel(
    title = title,
    thumbnailUrl = thumbnailUrl
)
