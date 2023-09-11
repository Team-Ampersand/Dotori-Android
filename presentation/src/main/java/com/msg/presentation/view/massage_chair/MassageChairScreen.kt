package com.msg.presentation.view.massage_chair

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.card.DotoriStudentCard
import com.dotori.dotori_components.components.utils.Types
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.view.massage_chair.component.DotoriTopBar
import com.msg.presentation.view.massage_chair.component.EmptyMassageChairScreen
import com.msg.presentation.view.massage_chair.component.MassageChairTopBar

@Composable
fun MassageChairScreen() {
    val list = listOf(1,2,3,4,5)

    if (list.isEmpty()) MassageChairIsEmptyContent()
    else MassageChairStudentListContent(list)
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MassageChairStudentListContent(list: List<Int>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { DotoriTopBar(onSwitchClick = {}) }

        item {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = DotoriTheme.colors.background
            )
        }

        stickyHeader { MassageChairTopBar() }

        items(list) { position ->
            DotoriStudentCard(
                name = "임가람",
                gender = "MAN",
                role = "ROLE_MEMBER",
                studentNumber = "3412",
                position = position,
                mode = Types.CardType.MASSAGE_CHAIR,
                isLast = list.lastIndex + 1 == position,
                onCheckBoxChange = {}
            )
        }
    }
}

@Composable
fun MassageChairIsEmptyContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        DotoriTopBar(onSwitchClick = {})
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = DotoriTheme.colors.background
        )
        MassageChairTopBar()
        EmptyMassageChairScreen()
    }
}

@Preview
@Composable
private fun MassageChairScreenPreview() {
    MassageChairScreen()
}