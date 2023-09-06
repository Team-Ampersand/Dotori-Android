package com.msg.presentation.view.self_study

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dotori.dotori_components.components.card.DotoriStudentCard
import com.dotori.dotori_components.components.utils.Types
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.FilterIcon

@Composable
fun SelfStudyScreen() {
    var checkBoxState by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        val list = listOf(1,2,3,4,5)

        item {
            TopAppBar(
                title = {
                    Text(
                        text = "자습신청",
                        style = DotoriTheme.typography.subTitle1,
                        color = DotoriTheme.colors.neutral10
                    )
                },
                backgroundColor = DotoriTheme.colors.cardBackground,
                actions = { FilterIcon() }
            )
        }

        items(list) { position ->
            DotoriStudentCard(
                name = "임가람",
                gender = "MAN",
                role = "ROLE_STUDENT",
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