package com.msg.dotori

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.msg.presentation.view.find_password.navigation.findPasswordScreen
import com.msg.presentation.view.login.navigation.loginScreen
import com.msg.presentation.view.massage_chair.navigation.massageChairScreen
import com.msg.presentation.view.music.navigation.musicScreen
import com.msg.presentation.view.self_study.navigation.selfStudyScreen
import com.msg.presentation.view.signup.navigation.signUpScreen
import com.msg.presentation.view.student_info.navigation.studentInfoScreen

@Composable
fun DotoriNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginScreen()
        findPasswordScreen()
        signUpScreen()
        massageChairScreen()
        selfStudyScreen()
        musicScreen()
        studentInfoScreen()
    }
}