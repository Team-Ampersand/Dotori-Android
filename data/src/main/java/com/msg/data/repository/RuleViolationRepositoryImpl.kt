package com.msg.data.repository

import com.msg.data.remote.datasource.rule_violation.RuleViolationDataSource
import com.msg.data.remote.dto.rule_violation.requset.asRuleViolationRequest
import com.msg.data.remote.dto.rule_violation.response.asRuleViolationResponseModel
import com.msg.data.remote.dto.rule_violation.response.asSearchRuleViolationResponseModel
import com.msg.domain.model.rule_violation.request.RuleViolationRequestModel
import com.msg.domain.model.rule_violation.response.RuleViolationResponseModel
import com.msg.domain.model.rule_violation.response.SearchRuleViolationResponseModel
import com.msg.domain.repository.RuleViolationRepository
import javax.inject.Inject

class RuleViolationRepositoryImpl @Inject constructor(
    private val ruleViolationDataSource: RuleViolationDataSource
): RuleViolationRepository {
    override suspend fun getDetailRuleViolation(
        role: String,
        stuNum: String
    ): List<RuleViolationResponseModel> = ruleViolationDataSource.getDetailRuleViolation(
        role = role,
        stuNum = stuNum
    ).map { it.asRuleViolationResponseModel() }

    override suspend fun getSelfRuleViolation(role: String): List<RuleViolationResponseModel> =
        ruleViolationDataSource.getSelfRuleViolation(role).map { it.asRuleViolationResponseModel() }

    override suspend fun searchRuleViolation(
        role: String,
        memberName: String,
        stuNum: String,
        gender: String
    ): List<SearchRuleViolationResponseModel> = ruleViolationDataSource.searchRuleViolation(
        role = role,
        memberName = memberName,
        stuNum = stuNum,
        gender = gender
    ).map { it.asSearchRuleViolationResponseModel() }

    override suspend fun postRuleViolation(
        role: String,
        ruleViolationRequest: RuleViolationRequestModel
    ) = ruleViolationDataSource.postRuleViolation(
        role = role,
        ruleViolationRequest = ruleViolationRequest.asRuleViolationRequest()
    )

    override suspend fun deleteRuleViolation(
        role: String,
        ruleId: String
    ) = ruleViolationDataSource.deleteRuleViolation(
        role = role,
        ruleId = ruleId
    )
}