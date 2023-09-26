package com.msg.domain.usecase.rule_violation

import com.msg.domain.repository.RuleViolationRepository
import javax.inject.Inject

class DeleteRuleViolationUseCase @Inject constructor(
    private val ruleViolationRepository: RuleViolationRepository
) {
    suspend operator fun invoke(
        role: String,
        ruleId: String
    ) = kotlin.runCatching {
        ruleViolationRepository.deleteRuleViolation(
            role = role,
            ruleId = ruleId
        )
    }
}