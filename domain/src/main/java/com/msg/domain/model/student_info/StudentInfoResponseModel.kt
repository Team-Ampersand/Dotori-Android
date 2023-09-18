package com.msg.domain.model.student_info

data class StudentInfoResponseModel(
    val id: Long,
    val gender: String,
    val memberName: String,
    val stuNum: String,
    val role: String,
    val selfStudyStatus: SelfStudyStatusModel
)
