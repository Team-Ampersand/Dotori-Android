package com.msg.presentation.view.self_study.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.self_study.SelfStudyScreen

const val selfStudyRoute = "self_study_route"

fun NavController.navigateToSelfStudy() {
    this.navigate(selfStudyRoute)
}

fun NavGraphBuilder.selfStudyScreen() {
    composable(selfStudyRoute) {
        SelfStudyScreen()
    }
}