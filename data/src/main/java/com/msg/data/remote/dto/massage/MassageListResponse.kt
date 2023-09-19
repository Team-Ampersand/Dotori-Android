package com.msg.data.remote.dto.massage

import com.msg.domain.model.massage.MassageListResponseModel

data class MassageListResponse(
    val rank: Int,
    val id: Int,
    val stuNum: String,
    val memberName: String,
    val gender: String
)

fun MassageListResponse.asMassageListResponseModel() = MassageListResponseModel(
    rank = rank,
    id = id,
    stuNum = stuNum,
    memberName = memberName,
    gender = gender
)