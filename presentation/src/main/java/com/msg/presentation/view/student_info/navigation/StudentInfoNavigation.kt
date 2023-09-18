package com.msg.presentation.view.student_info.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.student_info.StudentInfoScreen

const val studentInfoRoute = "student_info_route"

fun NavController.navigateToStudentInfo() {
    this.navigate(studentInfoRoute)
}

fun NavGraphBuilder.studentInfoScreen() {
    composable(studentInfoRoute) {
        StudentInfoScreen()
    }
}