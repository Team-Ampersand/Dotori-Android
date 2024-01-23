package com.msg.presentation.view.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.login.LoginScreen

const val loginRoute = "login_route"

fun NavController.navigateToLogin() {
    this.navigate(loginRoute)
}

fun NavGraphBuilder.loginScreen(navigateToMain: () -> Unit) {
    composable(loginRoute) {
        LoginScreen(navigateToMain = navigateToMain)
    }
}