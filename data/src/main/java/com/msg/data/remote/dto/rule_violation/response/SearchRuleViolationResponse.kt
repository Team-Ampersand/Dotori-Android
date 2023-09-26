package com.msg.data.remote.dto.rule_violation.response

import com.google.gson.annotations.SerializedName

data class SearchRuleViolationResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("memberName") val memberName: String,
    @SerializedName("stuNum") val stuNum: String,
    @SerializedName("selfStudyStatus") val selfStudyStatus: Boolean,
    @SerializedName("rule") val rule: List<String>
)
