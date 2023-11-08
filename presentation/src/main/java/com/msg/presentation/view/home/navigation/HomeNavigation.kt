package com.msg.presentation.view.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.home.HomeScreen

const val mainRoute = "main_route"

fun NavController.navigateToMain() {
    this.navigate(mainRoute)
}

fun NavGraphBuilder.mainScreen() {
    composable(mainRoute) {
        HomeScreen()
    }
}