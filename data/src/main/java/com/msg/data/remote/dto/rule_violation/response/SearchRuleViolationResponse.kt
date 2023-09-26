package com.msg.data.remote.dto.rule_violation.response

data class SearchRuleViolationResponse(
    val id: Long,
    val memberName: String,
    val stuNum: String,
    val selfStudyStatus: Boolean,
    val rule: List<String>
)
