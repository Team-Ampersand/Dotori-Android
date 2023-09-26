package com.msg.domain.usecase.rule_violation

import com.msg.domain.repository.RuleViolationRepository
import javax.inject.Inject

class SearchRuleViolationUseCase @Inject constructor(
    private val ruleViolationRepository: RuleViolationRepository
) {
    suspend operator fun invoke(
        role: String,
        memberName: String,
        stuNum: String,
        gender: String
    ) = kotlin.runCatching {
        ruleViolationRepository.searchRuleViolation(
            role = role,
            memberName = memberName,
            stuNum = stuNum,
            gender = gender
        )
    }
}