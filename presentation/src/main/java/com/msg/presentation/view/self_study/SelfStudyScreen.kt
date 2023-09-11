package com.msg.presentation.view.self_study

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dotori.dotori_components.components.bottomsheet.DotoriBottomSheetDialog
import com.dotori.dotori_components.components.card.DotoriStudentCard
import com.dotori.dotori_components.components.utils.Types
import com.msg.presentation.view.self_study.component.BottomSheetContent
import com.msg.presentation.view.self_study.component.DotoriTopBar
import com.msg.presentation.view.self_study.component.EmptySelfStudyScreen
import com.msg.presentation.view.self_study.component.SelfStudyTopBar
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SelfStudyScreen() {
    val list = listOf(1,2,3,4,5)
    val scope = rememberCoroutineScope()
    var sheetCloseState by remember { mutableStateOf(false) }

    DotoriBottomSheetDialog(
        sheetContent = {
            BottomSheetContent { searchText, grade, `class`, gender ->
                sheetCloseState = true
            }
        }
    ) { state ->
        LaunchedEffect(sheetCloseState) {
            state.hide()
            sheetCloseState = false
        }

        if (list.isEmpty()) {
            SelfStudyIsEmptyContent {
                scope.launch { state.show() }
            }
        } else {
            SelfStudyStudentListContent(
                list = list,
                onFilterIconClick = {
                    scope.launch { state.show() }
                }
            )
        }
    }
}

@Composable
fun SelfStudyIsEmptyContent(onFilterIconClick: () -> Unit, ) {
    Column(modifier = Modifier.fillMaxSize()) {
        DotoriTopBar(onSwitchClick = { /*TODO*/ })

        SelfStudyTopBar { onFilterIconClick() }

        EmptySelfStudyScreen()
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun SelfStudyStudentListContent(
    list: List<Int>,
    onFilterIconClick: () -> Unit
) {
    var checkBoxState by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item { DotoriTopBar(onSwitchClick = { /*TODO*/ }) }

        stickyHeader { SelfStudyTopBar { onFilterIconClick() } }

        items(list) { position ->
            DotoriStudentCard(
                name = "임가람",
                gender = "MAN",
                role = "ROLE_MEMBER",
                studentNumber = "3412",
                position = position,
                mode = Types.CardType.SELF_STUDY,
                isLast = list.lastIndex + 1 == position,
                onCheckBoxChange = { checkBoxState = it }
            )
        }
    }
}

@Preview
@Composable
fun SelfStudyScreenPreview() {
    SelfStudyScreen()
}