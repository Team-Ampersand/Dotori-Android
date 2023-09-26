package com.msg.data.remote.datasource.rule_violation

import com.msg.data.remote.dto.rule_violation.requset.RuleViolationRequest
import com.msg.data.remote.dto.rule_violation.response.RuleViolationResponse
import com.msg.data.remote.dto.rule_violation.response.SearchRuleViolationResponse

interface RuleViolationDataSource {
    suspend fun getDetailRuleViolation(
        role: String,
        stuNum: String
    ): List<RuleViolationResponse>

    suspend fun getSelfRuleViolation(role: String): List<RuleViolationResponse>

    suspend fun searchRuleViolation(
        role: String,
        memberName: String,
        stuNum: String,
        gender: String
    ): List<SearchRuleViolationResponse>

    suspend fun postRuleViolation(
        role: String,
        ruleViolationRequest: RuleViolationRequest
    )

    suspend fun deleteRuleViolation(
        role: String,
        ruleId: String
    )
}