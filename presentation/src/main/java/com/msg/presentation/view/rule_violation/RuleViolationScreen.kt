package com.msg.presentation.view.rule_violation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.dialog.DotoriDialog
import com.dotori.dotori_components.components.drawer.DotoriDrawerView
import com.dotori.dotori_components.components.drawer.DrawerItemData
import com.dotori.dotori_components.components.drawer.Icons
import com.dotori.dotori_components.components.rule.DotoriRoleViolateListItem
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.view.rule_violation.component.RuleViolationDialogContent
import com.msg.presentation.view.rule_violation.component.RuleViolationDialogType
import com.msg.presentation.view.rule_violation.component.RuleViolationHeader
import com.msg.presentation.view.student_info.component.DotoriHamburgerTopBar
import com.msg.presentation.view.util.updateDotoriTheme

@Composable
fun RuleViolationScreen(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var currentBottomSheetType by remember { mutableStateOf(RuleViolationDialogType.LIST) }

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
        if (showDialog) {
            DotoriDialog(onDismiss = { showDialog = false }) {
                RuleViolationDialogContent(
                    ruleViolationDialogType = currentBottomSheetType,
                    onDismiss = { showDialog = false }
                )
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
                onWriteRuleViolationClick = {
                    currentBottomSheetType = RuleViolationDialogType.CHECK
                    showDialog = true
                },
                onDownloadClick = { /*TODO*/ },
                onFilterClick = { /*TODO*/ }
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn {
                items(15) {
                    DotoriRoleViolateListItem(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        imageUrl = "",
                        name = "김준",
                        studentId = "1101",
                        gender = "MAN",
                        violate = "전자기기 소지, 반입 - 사행성기구외 2개",
                        shape = RoundedCornerShape(
                            topStart = if (it == 0) 16.dp else 0.dp,
                            topEnd = if (it == 0) 16.dp else 0.dp,
                            bottomStart = if (it == 14) 16.dp else 0.dp,
                            bottomEnd = if (it == 14) 16.dp else 0.dp // 나중에 list로 받아와서 조건문을 수정할 거같습니다.
                        )
                    ) {
                        currentBottomSheetType = RuleViolationDialogType.LIST
                        showDialog = true
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