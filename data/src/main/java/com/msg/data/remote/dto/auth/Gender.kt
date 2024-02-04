package com.msg.data.remote.dto.auth

import com.msg.domain.model.auth.GenderModel

enum class Gender {
    MAN,
    WOMAN
}

fun GenderModel.asRole() = when (this) {
    GenderModel.MAN -> Gender.MAN
    GenderModel.WOMAN -> Gender.WOMAN
}