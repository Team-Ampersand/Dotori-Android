package com.msg.data.remote.dto.rule_violation.requset

import com.google.gson.annotations.SerializedName
import com.msg.domain.model.rule_violation.request.RuleViolationRequestModel

data class RuleViolationRequest(
    @SerializedName("stuNum") val stuNum: List<String>,
    @SerializedName("rule") val rule: List<String>,
    @SerializedName("date") val date: String
)

fun RuleViolationRequestModel.asRuleViolationRequest() = RuleViolationRequest(
    stuNum = stuNum,
    rule = rule,
    date = date
)