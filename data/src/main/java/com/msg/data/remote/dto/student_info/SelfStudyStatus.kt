package com.msg.data.remote.dto.student_info

import com.msg.domain.model.student_info.SelfStudyStatusModel

enum class SelfStudyStatus {
    CAN,
    APPLIED,
    CANT,
    IMPOSSIBLE
}

fun SelfStudyStatus.asSelfStudyStatusModel() = when (this) {
    SelfStudyStatus.CAN -> SelfStudyStatusModel.CAN
    SelfStudyStatus.APPLIED -> SelfStudyStatusModel.APPLIED
    SelfStudyStatus.CANT -> SelfStudyStatusModel.CANT
    SelfStudyStatus.IMPOSSIBLE -> SelfStudyStatusModel.IMPOSSIBLE
}