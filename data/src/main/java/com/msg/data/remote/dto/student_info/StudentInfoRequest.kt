package com.msg.data.remote.dto.student_info

data class StudentInfoRequest(
    val userId: Long,
    val memberName: String,
    val stuNum: String,
    val gender: String,
    val role: String
)
