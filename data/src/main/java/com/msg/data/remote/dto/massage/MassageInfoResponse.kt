package com.msg.data.remote.dto.massage

import com.msg.domain.model.massage.MassageInfoResponseModel

data class MassageInfoResponse(
    val count: Int,
    val limit: Int,
    val massageStatus: String
)

fun MassageInfoResponse.asMassageInfoResponseModel() = MassageInfoResponseModel(
    count = count,
    limit = limit,
    massageStatus = massageStatus
)