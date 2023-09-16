package com.msg.data.remote.dto.student_info

import com.msg.domain.model.student_info.StudentInfoResponseModel

data class StudentInfoResponse(
    val id: Long,
    val gender: String,
    val memberName: String,
    val stuNum: String,
    val role: String,
    val selfStudyStatus: SelfStudyStatus
)

fun StudentInfoResponse.asStudentInfoResponseModel() = StudentInfoResponseModel(
    id = id,
    gender = gender,
    memberName = memberName,
    stuNum = stuNum,
    role = role,
    selfStudyStatus = selfStudyStatus.asSelfStudyStatusModel()
)
