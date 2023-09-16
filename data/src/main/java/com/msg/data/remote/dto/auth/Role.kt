package com.msg.data.remote.dto.auth

import com.msg.domain.model.auth.RoleModel


enum class Role {
    ROLE_ADMIN,
    ROLE_MEMBER,
    ROLE_COUNCILLOR,
    ROLE_DEVELOPER
}

fun Role.asRoleModel() = when (this) {
    Role.ROLE_ADMIN -> RoleModel.ROLE_ADMIN
    Role.ROLE_MEMBER -> RoleModel.ROLE_MEMBER
    Role.ROLE_COUNCILLOR -> RoleModel.ROLE_COUNCILLOR
    Role.ROLE_DEVELOPER -> RoleModel.ROLE_DEVELOPER
}