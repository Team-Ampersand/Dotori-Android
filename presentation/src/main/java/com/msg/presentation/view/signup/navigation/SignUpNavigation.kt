package com.msg.presentation.view.signup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.signup.SignUpScreen

const val signUpRoute = "sign_up_route"

fun NavController.navigateToSignUp() {
    this.navigate(signUpRoute)
}

fun NavGraphBuilder.signUpScreen() {
    composable(signUpRoute) {
        SignUpScreen()
    }
}