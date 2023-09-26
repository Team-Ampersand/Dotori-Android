package com.msg.domain.repository

import com.msg.domain.model.rule_violation.request.RuleViolationRequestModel
import com.msg.domain.model.rule_violation.response.RuleViolationResponseModel
import com.msg.domain.model.rule_violation.response.SearchRuleViolationResponseModel

interface RuleViolationRepository {
    suspend fun getDetailRuleViolation(
        role: String,
        stuNum: String
    ): List<RuleViolationResponseModel>

    suspend fun getSelfRuleViolation(role: String): List<RuleViolationResponseModel>

    suspend fun searchRuleViolation(
        role: String,
        memberName: String,
        stuNum: String,
        gender: String
    ): List<SearchRuleViolationResponseModel>

    suspend fun postRuleViolation(
        role: String,
        ruleViolationRequest: RuleViolationRequestModel
    )

    suspend fun deleteRuleViolation(
        role: String,
        ruleId: String
    )
}