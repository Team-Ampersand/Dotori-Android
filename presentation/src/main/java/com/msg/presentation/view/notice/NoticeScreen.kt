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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.notice.DotoriNoticeListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.White
import com.msg.presentation.view.music.component.DotoriTopBar
import com.msg.presentation.view.notice.component.MonthDivider
import com.msg.presentation.view.notice.component.NoticeDialogContent
import com.msg.presentation.view.notice.component.NoticeHeader
import com.msg.presentation.view.util.updateDotoriTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoticeScreen() {
    var isEditable by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val selectedList = remember { mutableListOf<Long>() }
    val noticeList = listOf(12, 11, 10, 6, 6, 6, 5, 4, 3, 2, 1, 1)
    val grouped = noticeList.groupBy { it }

    if (showDialog) {
        DotoriDialog(onDismiss = { showDialog = false }) {
            NoticeDialogContent(
                count = selectedList.size,
                onDismiss = { showDialog = false },
                onDelete = { /* TODO */ }
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
                NoticeHeader(
                    isEditable = isEditable,
                    onEditClick = { isEditable = !isEditable },
                    onWriteClick = { /* TODO: NoticeWriteScreen 이동 */ },
                    onDeleteClick = { showDialog = true }
                )
            }
            item {
                Divider(
                    thickness = 16.dp,
                    color = DotoriTheme.colors.cardBackground
                )
            }
            grouped.forEach { (month, notice) ->
                item {
                    /* TODO: Notice model이 작성된 후 날짜 로직으로 교체 */
                    if (month != grouped.keys.first()) {
                        MonthDivider(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                            month = month
                        )
                    }
                }
                items(notice.size) {
                    DotoriNoticeListItem(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = if (it != 0) 12.dp else 0.dp),
                        writer = "도토리",
                        title = "[기숙사 자습실 사용 관련 공지]",
                        content = "많은 분들이 급식의 화살표를 눌렀을때 날짜만 변경되는 점이 불편하다고 하여 이제는 급식",
                        date = "2023.${notice[it]}.28",
                        role = "ROLE_DEVELOPER",
                    ) {
                        if (isEditable) {
                            /* TODO: Notice model의 id로 변경 */
                            selectedList.add(notice[it].toLong())
                        } else {
                            /* TODO: NoticeDetailScreen 이동 */
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NoticeScreenPreview() {
    NoticeScreen()
}


