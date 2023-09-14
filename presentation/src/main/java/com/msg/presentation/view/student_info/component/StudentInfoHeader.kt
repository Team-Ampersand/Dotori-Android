package com.msg.presentation.view.student_info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.FilterIcon

@Composable
fun StudentInfoHeader(
    modifier: Modifier = Modifier,
    onFilterClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DotoriTheme.colors.cardBackground)
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = "학생정보",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )
        Spacer(modifier = Modifier.weight(1f))
        FilterIcon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onFilterClick
            ),
            tint = DotoriTheme.colors.neutral20
        )
    }
}

@Preview
@Composable
fun StudentInfoHeaderPreview() {
    StudentInfoHeader {}
}