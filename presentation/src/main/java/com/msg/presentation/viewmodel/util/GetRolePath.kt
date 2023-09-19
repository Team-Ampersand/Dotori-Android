package com.msg.presentation.viewmodel.util

import com.msg.domain.usecase.auth.GetRoleUseCase

suspend fun GetRoleUseCase.getRolePath(): String {
    return when (this.invoke().getOrDefault("")) {
        "ROLE_ADMIN" -> "admin"
        "ROLE_COUNCILLOR" -> "councillor"
        "ROLE_DEVELOPER" -> "developer"
        "ROLE_MEMBER" -> "member"
        else -> ""
    }
}
