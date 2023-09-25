package com.msg.presentation.view.notice

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.notice.DotoriNoticeListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.White
import com.msg.presentation.view.music.component.DotoriTopBar
import com.msg.presentation.view.notice.component.MonthDivider
import com.msg.presentation.view.notice.component.NoticeDialogContent
import com.msg.presentation.view.notice.component.NoticeHeader
import com.msg.presentation.view.util.updateDotoriTheme
import com.msg.presentation.viewmodel.notice.NoticeViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoticeScreen(noticeViewModel: NoticeViewModel = hiltViewModel()) {
    var isEditable by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val roleUiState by noticeViewModel.roleUiState.collectAsState()
    val noticeUiState by noticeViewModel.noticeUiState.collectAsState()

    LaunchedEffect(Unit) {
        noticeViewModel.getRole()
        noticeViewModel.getAllNotice(roleUiState.data!!)
    }

    val grouped = noticeUiState.data!!.groupBy { it.createdDate.year to it.createdDate.monthValue }
    val selectedList = remember { mutableListOf<Long>() }

    if (showDialog) {
        DotoriDialog(onDismiss = { showDialog = false }) {
            NoticeDialogContent(
                title = "${selectedList.size}개 항목 삭제",
                content = "정말로 ${selectedList.size}개의 항목을 삭제 하시겠습니까?",
                onDismiss = { showDialog = false },
                onConfirm = {
                    noticeViewModel.deleteNoticeByIdList(
                        role = roleUiState.data!!,
                        body = selectedList
                    )
                }
            )
        }
    }

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(modifier = Modifier.background(White)) {
            item {
                DotoriTopBar(
                    isDark = isSystemInDarkTheme(),
                    onSwitchClick = { DotoriTheme.updateDotoriTheme() }
                )
                Divider(
                    thickness = 1.dp,
                    color = DotoriTheme.colors.neutral40
                )
            }
            stickyHeader {
                if (roleUiState.data!! in listOf(
                        "ROLE_DEVELOPER",
                        "ROLE_COUNCILLOR",
                        "ROLE_ADMIN"
                    )
                ) {
                    NoticeHeader(
                        isEditable = isEditable,
                        onEditClick = { isEditable = !isEditable },
                        onWriteClick = { /* TODO: NoticeWriteScreen 이동 */ },
                        onDeleteClick = { showDialog = true }
                    )
                }
            }
            item {
                Divider(
                    thickness = 16.dp,
                    color = DotoriTheme.colors.cardBackground
                )
            }
            grouped.forEach { (yearMonth, notice) ->
                item {
                    if (yearMonth != grouped.keys.first()) {
                        MonthDivider(
                            modifier = Modifier.padding(
                                horizontal = 20.dp,
                                vertical = 16.dp
                            ),
                            month = yearMonth.second
                        )
                    }
                }
                items(notice.size) {
                    val formattedDate = DateTimeFormatter.ofPattern("yyyy.MM.dd").format(notice[it].createdDate)

                    DotoriNoticeListItem(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = if (it != 0) 12.dp else 0.dp),
                        writer = when (notice[it].role) {
                            "ROLE_DEVELOPER" -> "도토리"
                            "ROLE_COUNCILLOR" -> "기숙사자치위원회"
                            "ROLE_ADMIN" -> "사감선생님"
                            else -> "학생"
                        },
                        title = notice[it].title,
                        content = notice[it].content,
                        isFocus = notice[it].id in selectedList,
                        date = formattedDate,
                        role = notice[it].role,
                    ) {
                        if (isEditable) {
                            selectedList.changeNoticeSelected(notice[it].id)
                        } else {
                            /* TODO: NoticeDetailScreen 이동 */
                        }
                    }
                }
            }
        }
    }
}

private fun MutableList<Long>.changeNoticeSelected(noticeId: Long) {
    if (noticeId in this) this.remove(noticeId) else this.add(noticeId)
}

@Preview
@Composable
fun NoticeScreenPreview() {
    NoticeScreen()
}


