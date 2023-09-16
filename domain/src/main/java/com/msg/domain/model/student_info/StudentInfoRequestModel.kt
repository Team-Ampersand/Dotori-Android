package com.msg.domain.model.student_info

data class StudentInfoRequestModel(
    val userId: Long,
    val memberName: String,
    val stuNum: String,
    val gender: String,
    val role: String
)
