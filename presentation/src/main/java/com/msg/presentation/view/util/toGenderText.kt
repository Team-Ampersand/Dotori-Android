package com.msg.presentation.view.util

import com.msg.domain.model.auth.GenderModel

fun String.toGenderText(): GenderModel {
    return when (this) {
        "남" -> GenderModel.MAN
        "여" -> GenderModel.WOMAN
        else -> GenderModel.MAN
    }
}