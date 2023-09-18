package com.msg.presentation.view.find_password.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.find_password.FindPasswordScreen

const val findPasswordRoute = "find_password_route"

fun NavController.navigateToFindPassword() {
    this.navigate(findPasswordRoute)
}

fun NavGraphBuilder.findPasswordScreen() {
    composable(findPasswordRoute) {
        FindPasswordScreen()
    }
}