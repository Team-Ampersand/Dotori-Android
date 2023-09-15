package com.msg.data.remote.dto.student_info

data class StudentInfoResponse(
    val id: Long,
    val gender: String,
    val memberName: String,
    val stuNum: String,
    val role: String,
    val selfStudyStatus: SelfStudyStatus
)
