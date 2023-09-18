package com.msg.domain.model.self_study

data class SelfStudyListResponseModel(
    val rank: Int,
    val id: Int,
    val stuNum: String,
    val memberName: String,
    val gender: String,
    val selfStudyCheck: Boolean
)
