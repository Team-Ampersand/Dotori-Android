package com.msg.dotori

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.msg.presentation.view.find_password.navigation.findPasswordScreen
import com.msg.presentation.view.find_password.navigation.navigateToFindPassword
import com.msg.presentation.view.find_password.navigation.navigateToPasswordAuthentication
import com.msg.presentation.view.find_password.navigation.passwordAuthenticationScreen
import com.msg.presentation.view.home.navigation.mainScreen
import com.msg.presentation.view.home.navigation.navigateToMain
import com.msg.presentation.view.login.navigation.loginScreen
import com.msg.presentation.view.login.navigation.navigateToLogin
import com.msg.presentation.view.massage_chair.navigation.massageChairScreen
import com.msg.presentation.view.music.navigation.musicScreen
import com.msg.presentation.view.notice.navigation.navigateToNotice
import com.msg.presentation.view.notice.navigation.navigateToNoticeDetail
import com.msg.presentation.view.notice.navigation.navigateToNoticeEdit
import com.msg.presentation.view.notice.navigation.noticeDetailScreen
import com.msg.presentation.view.notice.navigation.noticeEditScreen
import com.msg.presentation.view.notice.navigation.noticeScreen
import com.msg.presentation.view.self_study.navigation.selfStudyScreen
import com.msg.presentation.view.signup.navigation.authenticationScreen
import com.msg.presentation.view.signup.navigation.navigateToAuthentication
import com.msg.presentation.view.signup.navigation.navigateToPassword
import com.msg.presentation.view.signup.navigation.navigateToSignUp
import com.msg.presentation.view.signup.navigation.passwordScreen
import com.msg.presentation.view.signup.navigation.signUpScreen
import com.msg.presentation.view.student_info.navigation.studentInfoScreen

@Composable
fun DotoriNavHost(
    navController: NavHostController,
    startDestination: String
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginScreen(
            navigateToMain = { navController.navigateToMain() },
            navigateToSignUp = { navController.navigateToSignUp() },
            navigateToFindPassword = { navController.navigateToPasswordAuthentication() }
        )
        signUpScreen(
            viewModelStoreOwner = viewModelStoreOwner,
            navigateToBack = { navController.popBackStack() },
            navigateToLogin = { navController.navigateToLogin() },
            navigateToAuthentication = { navController.navigateToAuthentication() }
        )
        authenticationScreen(
            viewModelStoreOwner = viewModelStoreOwner,
            navigateToBack = { navController.popBackStack() },
            navigateToLogin = { navController.navigateToLogin() },
            navigateToPassword = { navController.navigateToPassword() }
        )
        passwordScreen(
            viewModelStoreOwner = viewModelStoreOwner,
            navigateToBack = { navController.popBackStack() },
            navigateToLogin = { navController.navigateToLogin() }
        )
        passwordAuthenticationScreen(
            navigateToBack = { navController.popBackStack() },
            navigateToLogin = { navController.navigateToLogin() },
            navigateToFindPassword = { navController.navigateToFindPassword() }
        )
        findPasswordScreen(
            navigateToBack = { navController.popBackStack() },
            navigateToLogin = { navController.navigateToLogin() }
        )
        massageChairScreen()
        selfStudyScreen()
        musicScreen()
        studentInfoScreen()
        noticeScreen(
            navigateToNoticeEdit = { navController.navigateToNoticeEdit() },
            navigateToNoticeDetail = { navController.navigateToNoticeDetail(it.toString()) }
        )
        noticeDetailScreen(navigateToNoticeEdit = { navController.navigateToNoticeEdit(it.toString()) })
        noticeEditScreen(navigateToNotice = { navController.navigateToNotice() })
        mainScreen()
    }
}
