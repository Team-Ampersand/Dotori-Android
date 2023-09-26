package com.msg.data.remote.dto.rule_violation.response

import com.google.gson.annotations.SerializedName

data class RuleViolationResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("rule") val rule: String,
    @SerializedName("createdDate") val createdDate: String
)
