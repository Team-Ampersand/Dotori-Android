package com.msg.data.remote.dto.rule_violation.requset

data class RuleViolationRequest(
    val stuNum: List<String>,
    val rule: List<String>,
    val date: String
)