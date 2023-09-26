package com.msg.domain.usecase.rule_violation

import com.msg.domain.repository.RuleViolationRepository
import javax.inject.Inject

class GetSelfRuleViolationUseCase @Inject constructor(
    private val ruleViolationRepository: RuleViolationRepository
) {
    suspend operator fun invoke(role: String) = kotlin.runCatching { ruleViolationRepository.getSelfRuleViolation(role) }
}