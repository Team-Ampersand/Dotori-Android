package com.msg.domain.model.student_info

data class SearchStudentInfoResponseModel(
    val email: String,
    val memberName: String,
    val stuName: String,
    val gender: String,
    val role: String,
    val selfStudyCheck: Boolean
)
