package com.msg.presentation.view.notice.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.msg.presentation.view.notice.NoticeDetailScreen

const val noticeDetailRoute = "notice_detail_route"

fun NavController.navigateToNoticeDetail(noticeId: Long) {
    this.navigate("$noticeDetailRoute/$noticeId}")
}

fun NavGraphBuilder.noticeDetailScreen(navigateToNoticeEdit: (noticeId: Long) -> Unit) {
    composable(
        route = "$noticeDetailRoute/{noticeId}",
        arguments = listOf(navArgument("noticeId") { type = NavType.LongType })
    ) { backStackEntry ->
        NoticeDetailScreen(
            noticeId = backStackEntry.arguments?.getLong("noticeId"),
            navigateToNoticeEdit = navigateToNoticeEdit
        )
    }
}
