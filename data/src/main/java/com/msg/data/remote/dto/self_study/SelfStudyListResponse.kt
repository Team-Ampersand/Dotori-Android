package com.msg.data.remote.dto.self_study

import com.msg.domain.model.self_study.SelfStudyListResponseModel

data class SelfStudyListResponse(
    val rank: Int,
    val id: Int,
    val stuNum: String,
    val memberName: String,
    val gender: String,
    val selfStudyCheck: Boolean
)

fun SelfStudyListResponse.asSelfStudyListResponseModel() = SelfStudyListResponseModel(
    rank = rank,
    id = id,
    stuNum = stuNum,
    memberName = memberName,
    gender = gender,
    selfStudyCheck = selfStudyCheck
)