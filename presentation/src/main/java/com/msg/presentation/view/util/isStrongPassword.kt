package com.msg.presentation.view.util

fun isStrongPassword(password: String): Boolean {
    val regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,20}\$")
    return regex.matches(password)
}