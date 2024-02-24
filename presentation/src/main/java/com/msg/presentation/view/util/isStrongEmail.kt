package com.msg.presentation.view.util

fun isStrongEmail(email: String): Boolean {
    val regex = Regex("^s\\d{5}@gsm\\.hs\\.kr\$")
    return regex.matches(email)
}