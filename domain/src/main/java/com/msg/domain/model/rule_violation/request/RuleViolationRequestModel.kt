package com.msg.domain.model.rule_violation.request

data class RuleViolationRequestModel(
    val stuNum: List<String>,
    val rule: List<String>,
    val date: String
)
