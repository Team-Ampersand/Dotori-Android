package com.msg.domain.usecase.rule_violation

import com.msg.domain.repository.RuleViolationRepository
import javax.inject.Inject

class GetDetailRuleViolationUseCase @Inject constructor(
    private val ruleViolationRepository: RuleViolationRepository
) {
    suspend operator fun invoke(
        role: String,
        stuNum: String
    ) = kotlin.runCatching {
        ruleViolationRepository.getDetailRuleViolation(
            role = role,
            stuNum = stuNum
        )
    }
}