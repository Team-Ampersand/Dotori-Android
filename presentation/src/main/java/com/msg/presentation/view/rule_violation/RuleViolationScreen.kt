package com.msg.presentation.view.rule_violation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotori.dotori_components.components.bottomsheet.DotoriBottomSheetDialog
import com.dotori.dotori_components.components.calendar.DotoriCalendar
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.drawer.DotoriDrawerView
import com.dotori.dotori_components.components.drawer.DrawerItemData
import com.dotori.dotori_components.components.drawer.Icons
import com.dotori.dotori_components.components.rule.DotoriRoleViolateListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.view.rule_violation.component.RuleViolationCheckDialogContent
import com.msg.presentation.view.rule_violation.component.RuleViolationCreateDialogContent
import com.msg.presentation.view.rule_violation.component.RuleViolationDeleteDialogContent
import com.msg.presentation.view.rule_violation.component.RuleViolationHeader
import com.msg.presentation.view.rule_violation.component.RuleViolationListDialogContent
import com.msg.presentation.view.student_info.component.DotoriHamburgerTopBar
import com.msg.presentation.view.util.updateDotoriTheme
import com.msg.presentation.viewmodel.RuleViolationViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RuleViolationScreen(
    modifier: Modifier = Modifier,
    ruleViolationViewModel: RuleViolationViewModel = hiltViewModel()
) {
    var showListDialog by remember { mutableStateOf(false) }
    var showCheckDialog by remember { mutableStateOf(false) }
    var showCreateDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var studentList = remember { mutableStateListOf<String>() }
    val coroutineScope = rememberCoroutineScope()
    var createdDate = LocalDate.now()

    val detailRuleViolationUiState by ruleViolationViewModel.detailRuleViolationUiState.collectAsState()
    val studentInfoUiState by ruleViolationViewModel.studentInfoUiState.collectAsState()
    val deleteRuleViolationUiState by ruleViolationViewModel.deleteRuleViolationUiState.collectAsState()
    val selfRuleViolationUiState by ruleViolationViewModel.selfRuleViolationUiState.collectAsState()
    val searchRuleViolationUiState by ruleViolationViewModel.searchRuleViolationUiState.collectAsState()
    val postRuleViolationUiState by ruleViolationViewModel.postRuleViolationUiState.collectAsState()

    LaunchedEffect(Unit) {
        ruleViolationViewModel.getAllStudentInfo()
    }

    DotoriBottomSheetDialog(
        sheetContent = { DotoriCalendar(onDaySelected = { createdDate = it }) }
    ) { sheetState ->
        DotoriDrawerView(
            itemList = listOf(
                DrawerItemData(
                    title = "학생 정보",
                    icon = Icons.Person,
                    onClick = {}
                ),
                DrawerItemData(
                    title = "규정 위반",
                    icon = Icons.Violation,
                    onClick = {}
                )
            )
        ) { openDrawer ->
            if (showListDialog) {
                DotoriDialog(onDismiss = { showListDialog = false }) {
                    RuleViolationListDialogContent(
                        onDelete = {
                            showListDialog = false
                            showDeleteDialog = true
                            studentInfoUiState.data!!.map { studentInfo ->
                                detailRuleViolationUiState.data!!.map { detailRuleViolation ->
                                    ruleViolationViewModel.deleteRuleViolation(
                                        role = studentInfo.role,
                                        ruleId = detailRuleViolation.rule
                                    )
                                }
                            }
                        },
                        onDismiss = { showListDialog = false }
                    )
                }
            }

            if (showCheckDialog) {
                DotoriDialog(onDismiss = { showCheckDialog = false }) {
                    RuleViolationCheckDialogContent(
                        onPlus = {
                            showCheckDialog = false
                            showCreateDialog = true
                        },
                        onDismiss = { showCheckDialog = false }
                    )
                }
            }

            if (showCreateDialog) {
                DotoriDialog(onDismiss = { showCreateDialog = false }) {
                    RuleViolationCreateDialogContent(
                        createdDate = createdDate,
                        studentList = studentList,
                        onCalendar = { coroutineScope.launch { sheetState.show() } },
                        onPlus = {
                            showCreateDialog = false
                            showCheckDialog = true
                        },
                        onCreate = {},
                        onDismiss = { showCreateDialog = false }
                    )
                }
            }

            if (showDeleteDialog) {
                DotoriDialog(onDismiss = { showDeleteDialog = false }) {
                    detailRuleViolationUiState.data!!.map {
                        RuleViolationDeleteDialogContent(
                            ruleViolation = it.rule,
                            onSubmit = { showDeleteDialog = false },
                            onDismiss = { showDeleteDialog = false }
                        )
                    }
                }
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(DotoriTheme.colors.background)
            ) {
                DotoriHamburgerTopBar(
                    onHamburgerClick = { openDrawer() },
                    onSwitchClick = { DotoriTheme.updateDotoriTheme() }
                )
                Divider(
                    thickness = 1.dp,
                    color = DotoriTheme.colors.neutral40
                )
                RuleViolationHeader(
                    onWriteRuleViolationClick = { showCheckDialog = true },
                    onDownloadClick = { /*TODO*/ },
                    onFilterClick = { /*TODO*/ }
                )
                Spacer(modifier = Modifier.height(15.dp))
                LazyColumn {
                    itemsIndexed(studentInfoUiState.data!!) { index, studentInfo ->
                        DotoriRoleViolateListItem(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            imageUrl = "",
                            name = studentInfo.memberName,
                            studentId = studentInfo.stuNum,
                            gender = studentInfo.gender,
                            violate = detailRuleViolationUiState.data!![index].rule,
                            shape = RoundedCornerShape(
                                topStart = if (index == 0) 16.dp else 0.dp,
                                topEnd = if (index == 0) 16.dp else 0.dp,
                                bottomStart = if (index == 14) 16.dp else 0.dp,
                                bottomEnd = if (index == 14) 16.dp else 0.dp // 나중에 list로 받아와서 조건문을 수정할 거같습니다.
                            ),
                            onOptionClicked = {
                                studentList.add(studentInfo.memberName)
                                showListDialog = true
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
fun RuleViolationScreenPreview() {
    RuleViolationScreen()
}