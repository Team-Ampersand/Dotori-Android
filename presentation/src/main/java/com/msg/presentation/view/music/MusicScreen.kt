package com.msg.presentation.view.music

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.bottomsheet.DotoriBottomSheetDialog
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.music.DotoriMusicListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicScreen(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var currentBottomSheetType by remember { mutableStateOf<BottomSheetType?>(null) }
    var musicUrl by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val musicList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

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
                MusicDialogContent(
                    url = musicUrl,
                    onValueChange = { musicUrl = it }
                ) { /*TODO*/ }
            }
        }

        val showBottomSheet: (BottomSheetType) -> Unit = { sheetType ->
            currentBottomSheetType = sheetType
            coroutineScope.launch { sheetState.show() }
        }

        if (musicList.isEmpty()) {
            EmptyMusicContent(
                onSwitchClick = { /*TODO*/ },
                onMusicClick = { showDialog = true },
                onCalendarClick = { showBottomSheet(BottomSheetType.Calendar) }
            )
        } else {
            MusicListContent(
                musicList = musicList,
                onSwitchClick = { /*TODO*/ },
                onMusicClick = { showDialog = true },
                onCalendarClick = { showBottomSheet(BottomSheetType.Calendar) },
                onOptionClicked = { showBottomSheet(BottomSheetType.Option) },
                onItemClicked = { /*TODO*/ }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicListContent(
    musicList: List<Int>,
    onSwitchClick: (Boolean) -> Unit,
    onMusicClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onItemClicked: () -> Unit,
    onOptionClicked: () -> Unit
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Column(verticalArrangement = Arrangement.Center) {
                    DotoriTopBar(onSwitchClick = onSwitchClick)
                    Divider(
                        thickness = 1.dp,
                        color = DotoriTheme.colors.neutral40
                    )
                }
            }
            stickyHeader {
                MusicHeader(
                    onMusicClick = onMusicClick,
                    onCalendarClick = onCalendarClick
                )
            }
            item {
                Divider(
                    color = DotoriTheme.colors.background,
                    thickness = 16.dp
                )
            }
            items(musicList.size) {
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
                                    topStart = if (it == 0) 16.dp else 0.dp,
                                    topEnd = if (it == 0) 16.dp else 0.dp,
                                    bottomStart = if (musicList.lastIndex == it) 16.dp else 0.dp,
                                    bottomEnd = if (musicList.lastIndex == it) 16.dp else 0.dp
                                )
                            )
                            .padding(horizontal = 16.dp)
                            .padding(
                                top = if (it == 0) 16.dp else 8.dp,
                                bottom = if (musicList.lastIndex == it) 16.dp else 8.dp
                            ),
                        imageUrl = "",
                        title = "10cm- 서랍(그 해 우리는 OST Part.1)/가사 Audio Lyrics 21.12.07 New Release",
                        name = "2105 김준",
                        date = "16시 23분",
                        onItemClicked = onItemClicked,
                        onOptionClicked = onOptionClicked
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyMusicContent(
    onSwitchClick: (Boolean) -> Unit,
    onMusicClick: () -> Unit,
    onCalendarClick: () -> Unit
) {
    val imageRes = if (DotoriTheme.isSystemIsDarkTheme()) {
        R.drawable.ic_empty_music_icon_dark
    } else {
        R.drawable.ic_empty_music_icon_light
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DotoriTopBar(onSwitchClick = onSwitchClick)
        MusicHeader(
            onMusicClick = onMusicClick,
            onCalendarClick = onCalendarClick
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "note icon"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "아직 신청한 음악이 없습니다..",
                style = DotoriTheme.typography.subTitle2,
                color = DotoriTheme.colors.neutral10
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "오른쪽 위에서 음악 신청을 해보세요!",
                style = DotoriTheme.typography.caption,
                color = DotoriTheme.colors.neutral20
            )
        }
    }
}

@Preview
@Composable
fun MusicScreenTest() {
    MusicScreen()
}