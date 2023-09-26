package com.msg.presentation.view.notice.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.msg.presentation.view.notice.NoticeScreen

const val noticeRoute = "notice_route"

fun NavController.navigateToNotice() {
    this.navigate(noticeRoute)
}

fun NavGraphBuilder.noticeScreen(
    navigateToNoticeDetail: (noticeId: Long) -> Unit,
    navigateToNoticeEdit: () -> Unit
) {
    composable(noticeRoute) {
        NoticeScreen(
            navigateToNoticeDetail = navigateToNoticeDetail,
            navigateToNoticeEdit = navigateToNoticeEdit
        )
    }
}
