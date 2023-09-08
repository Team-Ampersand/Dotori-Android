package com.msg.presentation.view.music

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.bottomsheet.DotoriBottomSheetDialog
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.music.DotoriMusicListItem
import com.dotori.dotori_components.theme.DotoriTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MusicScreen(modifier: Modifier = Modifier) {
    var isDark by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var currentBottomSheetType by remember { mutableStateOf<BottomSheetType?>(null) }

    val coroutineScope = rememberCoroutineScope()

    DotoriBottomSheetDialog(
        sheetContent = {
            currentBottomSheetType?.let { sheetType ->
                BottomSheetContent(
                    bottomSheetType = sheetType,
                    onLinkClick = { /*TODO*/ },
                    onDeleteClick = { /*TODO*/ },
                    onDaySelected = { /*TODO*/ }
                )
            }
        }
    ) { sheetState ->
        if (showDialog) {
            DotoriDialog(onDismiss = { showDialog = false }) {
                MusicDialogContent(url = "") { /*TODO*/ }
            }
        }

        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                item {
                    Column(verticalArrangement = Arrangement.Center) {
                        DotoriTopBar { isDark = it }
                        Divider(
                            thickness = 1.dp,
                            color = DotoriTheme.colors.neutral40
                        )
                    }
                }
                stickyHeader {
                    MusicHeader(
                        onMusicClick = { showDialog = true },
                        onCalendarClick = {
                            currentBottomSheetType = BottomSheetType.Calendar
                            coroutineScope.launch { sheetState.show() }
                        }
                    )
                }
                item {
                    Divider(
                        color = DotoriTheme.colors.background,
                        thickness = 16.dp
                    )
                }
                val musicList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                items(musicList) {
                    Box(
                        modifier = Modifier
                            .background(color = DotoriTheme.colors.background)
                            .padding(horizontal = 20.dp)
                    ) {
                        DotoriMusicListItem(
                            modifier = Modifier
                                .background(
                                    color = DotoriTheme.colors.cardBackground,
                                    shape = RoundedCornerShape(
                                        topStart = if (musicList.first() == it) 16.dp else 0.dp,
                                        topEnd = if (musicList.first() == it) 16.dp else 0.dp,
                                        bottomStart = if (musicList.last() == it) 16.dp else 0.dp,
                                        bottomEnd = if (musicList.last() == it) 16.dp else 0.dp
                                    )
                                )
                                .padding(horizontal = 16.dp)
                                .padding(
                                    top = if (musicList.first() == it) 16.dp else 8.dp,
                                    bottom = if (musicList.last() == it) 16.dp else 8.dp
                                ),
                            imageUrl = "",
                            title = "10cm- 서랍(그 해 우리는 OST Part.1)/가사 Audio Lyrics 21.12.07 New Release",
                            name = "2105 김준",
                            date = "16시 23분",
                            onItemClicked = { /*TODO*/ },
                            onOptionClicked = {
                                currentBottomSheetType = BottomSheetType.Option
                                coroutineScope.launch { sheetState.show() }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MusicScreenTest() {
    MusicScreen()
}