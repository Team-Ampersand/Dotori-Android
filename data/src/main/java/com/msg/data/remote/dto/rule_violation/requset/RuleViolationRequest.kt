package com.msg.data.remote.dto.rule_violation.requset

import com.google.gson.annotations.SerializedName

data class RuleViolationRequest(
    @SerializedName("stuNum") val stuNum: List<String>,
    @SerializedName("rule") val rule: List<String>,
    @SerializedName("date") val date: String
)