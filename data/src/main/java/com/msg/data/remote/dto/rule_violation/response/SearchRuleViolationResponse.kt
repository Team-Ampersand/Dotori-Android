package com.msg.data.remote.dto.rule_violation.response

import com.google.gson.annotations.SerializedName
import com.msg.domain.model.rule_violation.response.SearchRuleViolationResponseModel

data class SearchRuleViolationResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("memberName") val memberName: String,
    @SerializedName("stuNum") val stuNum: String,
    @SerializedName("selfStudyStatus") val selfStudyStatus: Boolean,
    @SerializedName("rule") val rule: List<String>
)

fun SearchRuleViolationResponse.asSearchRuleViolationResponseModel() = SearchRuleViolationResponseModel(
    id = id,
    memberName = memberName,
    stuNum = stuNum,
    selfStudyStatus = selfStudyStatus,
    rule = rule
)