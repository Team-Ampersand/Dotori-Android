package com.msg.presentation.view.find_password.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.find_password.FindPasswordScreen
import com.msg.presentation.view.find_password.PasswordAuthenticationScreen

const val passwordAuthenticationRoute = "password_authentication_route"
const val findPasswordRoute = "find_password_route"

fun NavController.navigateToPasswordAuthentication() {
    this.navigate(passwordAuthenticationRoute)
}

fun NavGraphBuilder.passwordAuthenticationScreen(
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToFindPassword: () -> Unit
) {
    composable(passwordAuthenticationRoute) {
        PasswordAuthenticationScreen(
            navigateToBack = navigateToBack,
            navigateToLogin = navigateToLogin,
            navigateToFindPassword = navigateToFindPassword
        )
    }
}

fun NavController.navigateToFindPassword() {
    this.navigate(findPasswordRoute)
}

fun NavGraphBuilder.findPasswordScreen(
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(findPasswordRoute) {
        FindPasswordScreen(
            navigateToBack = navigateToBack,
            navigateToLogin = navigateToLogin
        )
    }
}