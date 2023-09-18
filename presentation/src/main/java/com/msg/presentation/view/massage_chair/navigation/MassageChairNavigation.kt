package com.msg.presentation.view.massage_chair.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.massage_chair.MassageChairScreen

const val massageChairRoute = "massage_chair_route"

fun NavController.navigateToMassageChair() {
    this.navigate(massageChairRoute)
}

fun NavGraphBuilder.massageChairScreen() {
    composable(massageChairRoute) {
        MassageChairScreen()
    }
}