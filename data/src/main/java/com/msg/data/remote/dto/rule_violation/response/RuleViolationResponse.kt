package com.msg.data.remote.dto.rule_violation.response

import com.google.gson.annotations.SerializedName
import com.msg.domain.model.rule_violation.response.RuleViolationResponseModel

data class RuleViolationResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("rule") val rule: String,
    @SerializedName("createdDate") val createdDate: String
)

fun RuleViolationResponse.asRuleViolationResponseModel() = RuleViolationResponseModel(
    id = id,
    rule = rule,
    createdDate = createdDate
)