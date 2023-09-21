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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.White
import com.msg.presentation.R
import com.msg.presentation.view.music.component.DotoriTopBar
import com.msg.presentation.view.notice.component.NoticeDetailHeader
import com.msg.presentation.view.notice.component.NoticeDetailTitle
import com.msg.presentation.view.util.updateDotoriTheme
import com.skydoves.landscapist.glide.GlideImage
import java.time.LocalDateTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoticeDetailScreen() {
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
                    onEditClick = { /* TODO: EditScreen 이동 처리 */ },
                    onDeleteClick = { /* TODO: 디자인 생긴 후 처리 */ }
                )
            }
            val images = listOf("")
            item {
                NoticeDetailTitle(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    title = "[기숙사 자습실 관련 공지]",
                    createdDate = LocalDateTime.now(),
                    role = "ROLE_DEVELOPER"
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .background(color = DotoriTheme.colors.background, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "기숙사 자습실 관련하여 공지드립니다.\n" +
                                "[ 기숙사 자습실 관련 공지 ]\n" +
                                "\n" +
                                "현재 기숙사에서 화요일까지 정보처리산업기사시험으로인해 금일 자습실 운영에 제한이 생겨 자습인원을 신청한 순서대로 40번째까지만 자습실을 이용하실수있는점 양해바랍니다.\n" +
                                "궁금한 점 있으시면 이시완#7244으로 문의주시기바랍니다.",
                        style = DotoriTheme.typography.body,
                        color = DotoriTheme.colors.neutral10
                    )
                    images.forEach {
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
    NoticeDetailScreen()
}

