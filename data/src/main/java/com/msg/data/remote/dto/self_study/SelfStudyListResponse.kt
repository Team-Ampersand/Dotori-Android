package com.msg.data.remote.dto.self_study

data class SelfStudyListResponse(
    val rank: Int,
    val id: Int,
    val stuNum: String,
    val memberName: String,
    val gender: String,
    val selfStudyCheck: Boolean
)
