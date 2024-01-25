package com.msg.presentation.view.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.signup.AuthenticationScreen
import com.msg.presentation.view.signup.PasswordScreen
import com.msg.presentation.view.signup.SignUpScreen

const val signUpRoute = "sign_up_route"
const val authenticationRoute = "authentication_route"
const val passwordRoute = "password_route"

fun NavController.navigateToSignUp() {
    this.navigate(signUpRoute)
}

fun NavGraphBuilder.signUpScreen(
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToAuthentication: () -> Unit
) {
    composable(signUpRoute) {
        SignUpScreen(
            navigateToBack = navigateToBack,
            navigateToLogin = navigateToLogin,
            navigateToAuthentication = navigateToAuthentication
        )
    }
}

fun NavController.navigateToAuthentication() {
    this.navigate(authenticationRoute)
}

fun NavGraphBuilder.authenticationScreen(
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToPassword: () -> Unit
) {
    composable(authenticationRoute) {
        AuthenticationScreen(
            navigateToBack = navigateToBack,
            navigateToLogin = navigateToLogin,
            navigateToPassword = navigateToPassword
        )
    }
}

fun NavController.navigateToPassword() {
    this.navigate(passwordRoute)
}

fun NavGraphBuilder.passwordScreen(
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(passwordRoute) {
        PasswordScreen(
            navigateToBack = navigateToBack,
            navigateToLogin = navigateToLogin
        )
    }
}