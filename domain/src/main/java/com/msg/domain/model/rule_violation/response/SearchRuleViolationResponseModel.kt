package com.msg.domain.model.rule_violation.response

data class SearchRuleViolationResponseModel(
    val id: Long,
    val memberName: String,
    val stuNum: String,
    val selfStudyStatus: Boolean,
    val rule: List<String>
)
