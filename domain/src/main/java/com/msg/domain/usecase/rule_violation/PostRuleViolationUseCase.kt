package com.msg.domain.usecase.rule_violation

import com.msg.domain.model.rule_violation.request.RuleViolationRequestModel
import com.msg.domain.repository.RuleViolationRepository
import javax.inject.Inject

class PostRuleViolationUseCase @Inject constructor(
    private val ruleViolationRepository: RuleViolationRepository
) {
    suspend operator fun invoke(
        role: String,
        ruleViolationRequest: RuleViolationRequestModel
    ) = kotlin.runCatching {
        ruleViolationRepository.postRuleViolation(
            role = role,
            ruleViolationRequest = ruleViolationRequest
        )
    }
}