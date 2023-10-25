package com.msg.presentation.view.notice.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msg.presentation.view.notice.NoticeEditScreen

const val noticeEditRoute = "notice_edit_route"

fun NavController.navigateToNoticeEdit(noticeId: Long? = null) {
    val optionalArgument = noticeId?.let { "?noticeId=$it" } ?: ""

    this.navigate("${noticeEditRoute}${optionalArgument}")
}

fun NavGraphBuilder.noticeEditScreen(navigateToNotice: () -> Unit) {
    composable(
        route = "$noticeEditRoute?noticeId={noticeId}",
        arguments = listOf(navArgument("noticeId") {
            type = NavType.LongType
            nullable = true
        })
    ) { backStackEntry ->
        NoticeEditScreen(
            noticeId = backStackEntry.arguments?.getLong("noticeId"),
            navigateToNotice = navigateToNotice
        )
    }
}
