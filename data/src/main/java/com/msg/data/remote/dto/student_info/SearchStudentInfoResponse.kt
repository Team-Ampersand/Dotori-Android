package com.msg.data.remote.dto.student_info

import com.msg.domain.model.student_info.SearchStudentInfoResponseModel

data class SearchStudentInfoResponse(
    val email: String,
    val memberName: String,
    val stuName: String,
    val gender: String,
    val role: String,
    val selfStudyCheck: Boolean
)

fun SearchStudentInfoResponse.asSearchStudentInfoResponseModel() = SearchStudentInfoResponseModel(
    email = email,
    memberName = memberName,
    stuName = stuName,
    gender = gender,
    role = role,
    selfStudyCheck = selfStudyCheck
)