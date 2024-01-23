package com.msg.presentation.view.notice.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.notice.NoticeDetailScreen

const val noticeDetailRoute = "notice_detail_route"

fun NavController.navigateToNoticeDetail(noticeId: String) {
    this.navigate("$noticeDetailRoute/$noticeId")
}

fun NavGraphBuilder.noticeDetailScreen(navigateToNoticeEdit: (noticeId: Long) -> Unit) {
    composable(
        route = "$noticeDetailRoute/{noticeId}"
    ) { backStackEntry ->
        NoticeDetailScreen(
            noticeId = backStackEntry.arguments?.getLong("noticeId")!!,
            navigateToNoticeEdit = navigateToNoticeEdit
        )
    }
}