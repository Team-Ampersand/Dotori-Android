package com.msg.data.remote.dto.student_info

data class SearchStudentInfoResponse(
    val email: String,
    val memberName: String,
    val stuName: String,
    val gender: String,
    val role: String,
    val selfStudyCheck: Boolean
)
