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
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotori.dotori_components.components.bottomsheet.DotoriBottomSheetDialog
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.drawer.DotoriDrawerView
import com.dotori.dotori_components.components.drawer.DrawerItemData
import com.dotori.dotori_components.components.drawer.Icons
import com.dotori.dotori_components.components.student_info.DotoriStudentInfoListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.domain.model.student_info.SelfStudyStatusModel
import com.msg.domain.model.student_info.StudentInfoRequestModel
import com.msg.presentation.view.student_info.component.DotoriHamburgerTopBar
import com.msg.presentation.view.student_info.component.StudentInfoBottomSheetContent
import com.msg.presentation.view.student_info.component.StudentInfoBottomSheetType
import com.msg.presentation.view.student_info.component.StudentInfoDialogContent
import com.msg.presentation.view.student_info.component.StudentInfoHeader
import com.msg.presentation.viewmodel.StudentInfoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun StudentInfoScreen(
    modifier: Modifier = Modifier,
    studentInfoViewModel: StudentInfoViewModel = hiltViewModel()
) {
    var currentBottomSheetType by remember { mutableStateOf(StudentInfoBottomSheetType.StudentInfo) }
    var isBaned by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("김준") }
    var studentId by remember { mutableStateOf("2105") }
    var userId by remember { mutableStateOf(0L) }
    var role by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var sheetCloseState by remember { mutableStateOf(false) }
    var dialogState by remember { mutableStateOf(false) }
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
                    onSelfStudyClick = {
                        dialogState = true
                        sheetCloseState = true
                    },
                    onDismiss = { sheetCloseState = true },
                    onSaveClick = {
                        studentInfoViewModel.modifyStudentInfo(
                            StudentInfoRequestModel(
                                userId = userId,
                                memberName = name,
                                stuNum = studentId,
                                gender = gender,
                                role = role
                            )
                        )
                        sheetCloseState = true
                    },
                    onSearchLogic = { searchText, grade, `class`, gender, role, selfStudyCheck ->
                        studentInfoViewModel.getSearchStudentInfo(
                            name = searchText,
                            grade = grade,
                            classNum = `class`,
                            gender = gender,
                            role = role,
                            selfStudy = selfStudyCheck
                        )
                    },
                )
            }
        }
    ) { sheetState ->
        LaunchedEffect(sheetCloseState) {
            sheetState.hide()
            sheetCloseState = false
            if (dialogState) showDialog = true
        }

        if (showDialog) {
            DotoriDialog(onDismiss = { showDialog = false }) {
                dialogState = false
                StudentInfoDialogContent(
                    isBaned = isBaned,
                    name = "김준",
                    onSubmit = {
                        if (isBaned) studentInfoViewModel.banCancelSelfStudy(
                            role = role,
                            userId = userId
                        )
                        else studentInfoViewModel.banSelfStudy(
                            role = role,
                            userId = userId
                        )
                        showDialog = false
                    },
                    onCancel = { showDialog = false }
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
                    studentInfoViewModel.getAllStudentInfo()
                    val allStudentInfoList = studentInfoViewModel.getAllStudentInfoUiState.value.data!!
                    items(allStudentInfoList.size) {
                        Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                            DotoriStudentInfoListItem(
                                imageUrl = "", // 아직 서버에서 imageUrl를 넘겨주지 않습니다
                                name = allStudentInfoList[it].memberName,
                                studentId = allStudentInfoList[it].stuNum,
                                gender = allStudentInfoList[it].gender,
                                role = allStudentInfoList[it].role
                            ) {
                                currentBottomSheetType = StudentInfoBottomSheetType.StudentInfo
                                userId = it.toLong()
                                name = allStudentInfoList[it].memberName
                                studentId = allStudentInfoList[it].stuNum
                                role = allStudentInfoList[it].role
                                gender = allStudentInfoList[it].gender
                                isBaned = when(allStudentInfoList[it].selfStudyStatus) {
                                    SelfStudyStatusModel.CAN -> { false } // 자습 가능
                                    SelfStudyStatusModel.APPLIED -> { false } // 자습 신청됨
                                    SelfStudyStatusModel.CANT -> { true } // 자습 불가(자습신청 후 취소했을 때)
                                    SelfStudyStatusModel.IMPOSSIBLE -> { true } // 자습 불가(강제로 자습 막았을 때)
                                }
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