package com.msg.data.remote.dto.student_info

import com.msg.domain.model.student_info.StudentInfoRequestModel

data class StudentInfoRequest(
    val userId: Long,
    val memberName: String,
    val stuNum: String,
    val gender: String,
    val role: String
)

fun StudentInfoRequestModel.asStudentInfoRequest() = StudentInfoRequest(
    userId = userId,
    memberName = memberName,
    stuNum = stuNum,
    gender = gender,
    role = role
)