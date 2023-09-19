package com.msg.presentation.view.self_study

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.dotori.dotori_components.components.card.DotoriStudentCard
import com.dotori.dotori_components.components.utils.Types
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.domain.model.self_study.SelfStudyListResponseModel
import com.msg.presentation.view.component.DotoriTopBar
import com.msg.presentation.view.self_study.component.BottomSheetContent
import com.msg.presentation.view.self_study.component.EmptySelfStudyScreen
import com.msg.presentation.view.self_study.component.SelfStudyTopBar
import com.msg.presentation.view.util.updateDotoriTheme
import com.msg.presentation.viewmodel.SelfStudyViewModel
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SelfStudyScreen(
    selfStudyViewModel: SelfStudyViewModel = hiltViewModel()
) {
    val selfStudyList by selfStudyViewModel.selfStudyListState.collectAsState()
    val role = "member"

    LaunchedEffect(Unit) {
        selfStudyViewModel.getSelfStudyList(role)
    }

    val scope = rememberCoroutineScope()
    var sheetCloseState by remember { mutableStateOf(false) }

    DotoriBottomSheetDialog(
        sheetContent = {
            BottomSheetContent { searchText, grade, `class`, gender ->
                selfStudyViewModel.searchSelfStudyStudent(
                    role = role,
                    name = searchText,
                    gender = gender,
                    classNum = `class`,
                    grade = grade.toInt()
                )
                sheetCloseState = true
            }
        }
    ) { state ->
        LaunchedEffect(sheetCloseState) {
            state.hide()
            sheetCloseState = false
        }

        if (selfStudyList.data.isNullOrEmpty()) {
            SelfStudyIsEmptyContent {
                scope.launch { state.show() }
            }
        } else {
            SelfStudyStudentListContent(
                list = selfStudyList.data!!,
                onFilterIconClick = {
                    scope.launch { state.show() }
                },
                checkSelfStudyLogic = { item, checkBoxState ->
                    selfStudyViewModel.checkSelfStudy(
                        role = role,
                        memberId = item.id.toLong(),
                        selfStudyCheck = checkBoxState
                    )
                }
            )
        }
    }
}

@Composable
fun SelfStudyIsEmptyContent(onFilterIconClick: () -> Unit, ) {
    Column(modifier = Modifier.fillMaxSize()) {
        DotoriTopBar(onSwitchClick = { DotoriTheme.updateDotoriTheme() })

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DotoriTheme.colors.background
        )

        SelfStudyTopBar { onFilterIconClick() }

        EmptySelfStudyScreen()
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun SelfStudyStudentListContent(
    list: List<SelfStudyListResponseModel>,
    onFilterIconClick: () -> Unit,
    checkSelfStudyLogic: (SelfStudyListResponseModel, Boolean) -> Unit
) {
    var checkBoxState by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item { DotoriTopBar(onSwitchClick = { DotoriTheme.updateDotoriTheme() }) }

        item {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = DotoriTheme.colors.background
            )
        }

        stickyHeader { SelfStudyTopBar { onFilterIconClick() } }

        itemsIndexed(list) { position, item ->
            DotoriStudentCard(
                name = item.memberName,
                gender = item.gender,
                role = "ROLE_MEMBER",   // 임시 권한
                studentNumber = item.stuNum,
                position = position,
                mode = Types.CardType.SELF_STUDY,
                isLast = list.lastIndex + 1 == position,
                onCheckBoxChange = {
                    checkBoxState = it
                    checkSelfStudyLogic(item, checkBoxState)
                }
            )
        }
    }
}

@Preview
@Composable
fun SelfStudyScreenPreview() {
    SelfStudyScreen()
}