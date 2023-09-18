package com.msg.data.remote.dto.self_study

import com.msg.domain.model.self_study.SelfStudyInfoResponseModel

data class SelfStudyInfoResponse(
    val count: Int,
    val limit: Int,
    val selfStudyStatus: String
)

fun SelfStudyInfoResponse.asSelfStudyInfoResponseModel() = SelfStudyInfoResponseModel(
    count = count,
    limit = limit,
    selfStudyStatus = selfStudyStatus
)