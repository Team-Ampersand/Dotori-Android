package com.msg.presentation.view.student_info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
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
import com.dotori.dotori_components.components.drawer.DotoriDrawerView
import com.dotori.dotori_components.components.drawer.DrawerItemData
import com.dotori.dotori_components.components.drawer.Icons
import com.dotori.dotori_components.components.student_info.DotoriStudentInfoListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.view.student_info.component.DotoriHamburgerTopBar
import com.msg.presentation.view.student_info.component.StudentInfoBottomSheetContent
import com.msg.presentation.view.student_info.component.StudentInfoBottomSheetType
import com.msg.presentation.view.student_info.component.StudentInfoDialogContent
import com.msg.presentation.view.student_info.component.StudentInfoHeader
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun StudentInfoScreen(modifier: Modifier = Modifier) {
    var currentBottomSheetType by remember { mutableStateOf<StudentInfoBottomSheetType?>(null) }
    var isBaned by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("김준") }
    var studentId by remember { mutableStateOf("2105") }
    var sheetCloseState by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    DotoriBottomSheetDialog(
        sheetContent = {
            currentBottomSheetType?.let { sheetType ->
                StudentInfoBottomSheetContent(
                    bottomSheetType = sheetType,
                    isBaned = isBaned,
                    name = name,
                    studentId = studentId,
                    onModifyClick = { currentBottomSheetType = StudentInfoBottomSheetType.ModifyStudentInfo },
                    onSelfStudyClick = { showDialog = true },
                    onDismiss = { sheetCloseState = true },
                    onSaveClick = { sheetCloseState = true  }
                )
            }
        }
    ) { sheetState ->
        LaunchedEffect(sheetCloseState) {
            sheetState.hide()
            sheetCloseState = false
        }

        if (showDialog) {
            DotoriDialog(onDismiss = { showDialog = false }) {
                StudentInfoDialogContent(
                    isBaned = false,
                    name = "김준",
                    onSubmit = { showDialog = false },
                    onDismiss = { showDialog = false }
                )
            }
        }

        DotoriDrawerView(
            itemList = listOf(
                DrawerItemData(
                    title = "학생 정보",
                    icon = Icons.Person,
                    onClick = {}),
                DrawerItemData(
                    title = "규정 위반",
                    icon = Icons.Violation,
                    onClick = {}
                )
            )
        ) { openDrawer ->
            CompositionLocalProvider(
                LocalOverscrollConfiguration provides null
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .background(DotoriTheme.colors.cardBackground)
                ) {
                    item {
                        DotoriHamburgerTopBar(
                            onHamburgerClick = { openDrawer() },
                            onSwitchClick = {}
                        )
                        Divider(
                            thickness = 1.dp,
                            color = DotoriTheme.colors.neutral40
                        )
                    }
                    stickyHeader {
                        StudentInfoHeader(
                            modifier = Modifier.padding(bottom = 8.dp),
                        ) {
                            currentBottomSheetType = StudentInfoBottomSheetType.Filter
                            coroutineScope.launch { sheetState.show() }
                        }
                    }
                    items(15) {
                        Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                            DotoriStudentInfoListItem(
                                imageUrl = "",
                                name = "김준",
                                studentId = "1101",
                                gender = "MAN",
                                role = when (it) { // 테스트용으로 디자인이랑 똑같이 할려고 when문을 사용했습니다
                                    3 -> "ROLE_COUNCILLOR"
                                    4 -> "ROLE_DEVELOPER"
                                    else -> "ROLE_MEMBER"
                                }
                            ) {
                                currentBottomSheetType = StudentInfoBottomSheetType.StudentInfo
                                isBaned = it == 3 // 테스트 용으로 넣었습니다. 원래는 자습금지여부를 넣어줍니다
                                coroutineScope.launch { sheetState.show() }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StudentInfoScreenPreview() {
    StudentInfoScreen()
}