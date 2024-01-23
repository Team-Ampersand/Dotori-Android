package com.msg.presentation.view.notice

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.White
import com.msg.presentation.R
import com.msg.presentation.view.music.component.DotoriTopBar
import com.msg.presentation.view.notice.component.NoticeDetailHeader
import com.msg.presentation.view.notice.component.NoticeDetailTitle
import com.msg.presentation.view.notice.component.NoticeDialogContent
import com.msg.presentation.view.util.updateDotoriTheme
import com.msg.presentation.viewmodel.notice.NoticeDetailViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoticeDetailScreen(
    noticeId: Long?,
    noticeDetailViewModel: NoticeDetailViewModel = hiltViewModel(),
    navigateToNoticeEdit: (noticeId: Long) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    val roleUiState by noticeDetailViewModel.roleUiState.collectAsState()
    val noticeUiState by noticeDetailViewModel.noticeUiState.collectAsState()

    LaunchedEffect(Unit) {
        noticeDetailViewModel.getRole()
        noticeDetailViewModel.getNoticeDetail(
            role = roleUiState.data!!,
            noticeId = checkNotNull(noticeId.toString())
        )
    }

    if (showDialog) {
        DotoriDialog(onDismiss = { showDialog = false }) {
            NoticeDialogContent(
                title = "공지사항 삭제",
                content = "정말로 해당 공지사항을 삭제하겠습니까?",
                onDismiss = { showDialog = false },
                onConfirm = {
                    noticeDetailViewModel.deleteNoticeById(
                        role = roleUiState.data!!,
                        noticeId = checkNotNull(noticeId.toString())
                    )
                }
            )
        }
    }

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
        ) {
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
                NoticeDetailHeader(
                    onBackClick = { /* TODO: 뒤로가기 이벤트 처리 */ },
                    onEditClick = { navigateToNoticeEdit(checkNotNull(noticeId)) },
                    onDeleteClick = { showDialog = true }
                )
            }
            val notice = noticeUiState.data!!
            item {
                NoticeDetailTitle(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    title = notice.title,
                    createdDate = notice.createdDate,
                    role = notice.role
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .background(
                            color = DotoriTheme.colors.background,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 16.dp
                        )
                ) {
                    Text(
                        text = notice.content,
                        style = DotoriTheme.typography.body,
                        color = DotoriTheme.colors.neutral10
                    )
                    notice.noticeImages.forEach {
                        Spacer(modifier = Modifier.height(16.dp))
                        GlideImage(
                            modifier = Modifier.fillMaxWidth(),
                            imageModel = { it },
                            previewPlaceholder = R.drawable.ic_empty_music_icon_light
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun NoticeDetailScreenPreview() {
//    NoticeDetailScreen()
}

