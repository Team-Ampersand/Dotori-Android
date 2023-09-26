package com.msg.data.remote.datasource.rule_violation

import com.msg.data.remote.dto.rule_violation.requset.RuleViolationRequest
import com.msg.data.remote.dto.rule_violation.response.RuleViolationResponse
import com.msg.data.remote.dto.rule_violation.response.SearchRuleViolationResponse
import com.msg.data.remote.network.RuleViolationApi
import com.msg.data.remote.util.safeApiCall
import javax.inject.Inject

class RuleViolationDataSourceImpl @Inject constructor(
    private val ruleViolationApi: RuleViolationApi
) : RuleViolationDataSource {
    override suspend fun getDetailRuleViolation(
        role: String,
        stuNum: String
    ): List<RuleViolationResponse> = safeApiCall {
        ruleViolationApi.getDetailRuleViolation(
            role = role,
            stuNum = stuNum
        )
    }

    override suspend fun getSelfRuleViolation(role: String): List<RuleViolationResponse> = safeApiCall {
        ruleViolationApi.getSelfRuleViolation(role)
    }

    override suspend fun searchRuleViolation(
        role: String,
        memberName: String,
        stuNum: String,
        gender: String
    ): List<SearchRuleViolationResponse> = safeApiCall {
        ruleViolationApi.searchRuleViolation(
            role = role,
            memberName = memberName,
            stuNum = stuNum,
            gender = gender
        )
    }

    override suspend fun postRuleViolation(
        role: String,
        ruleViolationRequest: RuleViolationRequest
    ) = safeApiCall {
        ruleViolationApi.postRuleViolation(
            role = role,
            ruleViolationRequest = ruleViolationRequest
        )
    }

    override suspend fun deleteRuleViolation(
        role: String,
        ruleId: String
    ) = safeApiCall {
        ruleViolationApi.deleteRuleViolation(
            role = role,
            ruleId = ruleId
        )
    }
}