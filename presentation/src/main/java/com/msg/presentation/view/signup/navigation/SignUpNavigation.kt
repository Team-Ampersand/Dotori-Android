package com.msg.presentation.view.signup.navigation

import androidx.lifecycle.ViewModelStoreOwner
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
    viewModelStoreOwner: ViewModelStoreOwner,
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToAuthentication: () -> Unit
) {
    composable(signUpRoute) {
        SignUpScreen(
            viewModelStoreOwner = viewModelStoreOwner,
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
    viewModelStoreOwner: ViewModelStoreOwner,
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToPassword: () -> Unit
) {
    composable(authenticationRoute) {
        AuthenticationScreen(
            viewModelStoreOwner = viewModelStoreOwner,
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
    viewModelStoreOwner: ViewModelStoreOwner,
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit
) {
    composable(passwordRoute) {
        PasswordScreen(
            viewModelStoreOwner = viewModelStoreOwner,
            navigateToBack = navigateToBack,
            navigateToLogin = navigateToLogin
        )
    }
}