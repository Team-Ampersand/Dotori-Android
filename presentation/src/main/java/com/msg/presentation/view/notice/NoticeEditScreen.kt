package com.msg.presentation.view.notice

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.White
import com.dotori.dotori_components.theme.XMarkIcon
import com.msg.presentation.view.music.component.DotoriTopBar
import com.msg.presentation.view.notice.component.NoticeEditDialogContent
import com.msg.presentation.view.notice.component.NoticeEditHeader
import com.msg.presentation.view.notice.component.NoticeEditImage
import com.msg.presentation.view.util.updateDotoriTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoticeEditScreen() {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DotoriDialog(onDismiss = { showDialog = false }) {
            NoticeEditDialogContent(
                onDismiss = { showDialog = false },
                onConfirm = { /* TODO: 뒤로가기 이벤트 처리 */ }
            )
        }
    }

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            contentPadding = PaddingValues(bottom = 40.dp)
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
                NoticeEditHeader(
                    onBackClick = { showDialog = true },
                    onSaveClick = { /* TODO: 공지 저장 이벤트 처리 */ }
                )
            }
            val createdDate = LocalDateTime.now()
            val formattedDate = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일").format(createdDate)
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "작성자: 도토리",
                            style = DotoriTheme.typography.subTitle1,
                            color = DotoriTheme.colors.neutral10
                        )
                        Text(
                            text = formattedDate,
                            style = DotoriTheme.typography.body2,
                            color = DotoriTheme.colors.neutral20
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    DotoriTextField(
                        value = title,
                        placeholder = "제목 (1 ~ 45)",
                        singleLine = true,
                        maxLines = 1,
                        trailingIcon = {
                            if (title.isNotEmpty()) {
                                XMarkIcon(
                                    modifier = Modifier.clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = { title = "" }
                                    )
                                )
                            }
                        },
                        onValueChange = { title = it }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    DotoriTextField(
                        modifier = Modifier.defaultMinSize(minHeight = 393.dp),
                        value = content,
                        placeholder = "내용 (1 ~ 5000자)",
                        onValueChange = { content = it }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
            val images = listOf("", "", "")
            item {
                NoticeEditImage(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    images = images
                )
            }
        }
    }
}

@Preview
@Composable
fun NoticeEditScreenPreview() {
    NoticeEditScreen()
}
