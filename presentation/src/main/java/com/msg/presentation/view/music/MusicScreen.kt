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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotori.dotori_components.components.bottomsheet.DotoriBottomSheetDialog
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.music.DotoriMusicListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.domain.model.music.request.MusicRequestModel
import com.msg.domain.model.music.response.MusicModel
import com.msg.presentation.R
import com.msg.presentation.viewmodel.MusicViewModel
import com.msg.presentation.viewmodel.util.Event
import com.msg.presentation.view.music.component.BottomSheetContent
import com.msg.presentation.view.music.component.BottomSheetType
import com.msg.presentation.view.music.component.DotoriTopBar
import com.msg.presentation.view.music.component.MusicDialogContent
import com.msg.presentation.view.music.component.MusicHeader
import com.msg.presentation.view.util.updateDotoriTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MusicScreen(
    modifier: Modifier = Modifier,
    musicViewModel: MusicViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var currentBottomSheetType by remember { mutableStateOf(BottomSheetType.Option) }
    var requestUrl by remember { mutableStateOf("") }
    var youtubeUrl by remember { mutableStateOf("") }
    var musicId by remember { mutableStateOf<Long>(-1) }
    var selectedDay by remember { mutableStateOf(LocalDate.now()) }

    val coroutineScope = rememberCoroutineScope()
    val roleState by musicViewModel.roleUiState.collectAsState()
    val musicState by musicViewModel.musicUiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        musicViewModel.getRole()
        musicViewModel.getMusics(role = roleState.data!!, date = selectedDay.toString())
    }

    DotoriBottomSheetDialog(
        sheetContent = {
            BottomSheetContent(
                bottomSheetType = currentBottomSheetType,
                onLinkClick = { uriHandler.openUri(youtubeUrl) },
                onDeleteClick = { musicViewModel.deleteMusic(role = roleState.data!!, musicId = musicId) },
                onDaySelected = { selectedDay = it }
            )
        }
    ) { sheetState ->
        if (showDialog) {
            DotoriDialog(onDismiss = { showDialog = false }) {
                MusicDialogContent(
                    url = requestUrl,
                    onValueChange = { requestUrl = it }
                ) {
                    musicViewModel.requestMusic(
                        role = roleState.data!!,
                        body = MusicRequestModel(url = requestUrl)
                    )
                }
            }
        }

        val showBottomSheet: (BottomSheetType) -> Unit = { sheetType ->
            currentBottomSheetType = sheetType
            coroutineScope.launch { sheetState.show() }
        }

        when (musicState) {
            is Event.Success -> {
                val musicList = musicState.data!!
                if (musicList.isEmpty()) {
                    EmptyMusicContent(
                        onSwitchClick = { DotoriTheme.updateDotoriTheme() },
                        onMusicClick = { showDialog = true },
                        onCalendarClick = { showBottomSheet(BottomSheetType.Calendar) }
                    )
                } else {
                    MusicListContent(
                        musicList = musicList,
                        onSwitchClick = { DotoriTheme.updateDotoriTheme() },
                        onMusicClick = { showDialog = true },
                        onCalendarClick = { showBottomSheet(BottomSheetType.Calendar) },
                        onOptionClicked = { id, url ->
                            musicId = id
                            youtubeUrl = url
                            showBottomSheet(BottomSheetType.Option)
                        },
                        onItemClicked = { uriHandler.openUri(it) }
                    )
                }
            }
            else -> { /*TODO*/ }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MusicListContent(
    musicList: List<MusicModel>,
    onSwitchClick: (Boolean) -> Unit,
    onMusicClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onItemClicked: (id: String) -> Unit,
    onOptionClicked: (id: Long, url: String) -> Unit
) {
    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Column(verticalArrangement = Arrangement.Center) {
                    DotoriTopBar(
                        isDark = DotoriTheme.isSystemIsDarkTheme(),
                        onSwitchClick = onSwitchClick
                    )
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
                    val createdLocalDateTime = LocalDateTime.parse(musicList[it].createdTime)

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
                        imageUrl = musicList[it].thumbnailUrl,
                        title = musicList[it].title,
                        name = "${musicList[it].stuNum} ${musicList[it].username}",
                        date = "${createdLocalDateTime.hour}시 ${createdLocalDateTime.minute}분",
                        onItemClicked = {
                            onItemClicked(musicList[it].url)
                        },
                        onOptionClicked = { 
                            onOptionClicked(musicList[it].id, musicList[it].url) 
                        }
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background)
    ) {
        Column {
            DotoriTopBar(
                isDark = DotoriTheme.isSystemIsDarkTheme(),
                onSwitchClick = onSwitchClick
            )
            MusicHeader(
                onMusicClick = onMusicClick,
                onCalendarClick = onCalendarClick
            )
        }
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
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
