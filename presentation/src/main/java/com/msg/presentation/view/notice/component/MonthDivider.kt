package com.msg.presentation.view.notice.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme

@Composable
fun MonthDivider(
    modifier: Modifier = Modifier,
    month: Int
) {
    Row(
        modifier = modifier.padding(horizontal = 38.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = DotoriTheme.colors.neutral40
        )
        /* TODO: Notice model이 작성된 후 year 값 설정 */
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "2023년 ${month}월",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
        Divider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = DotoriTheme.colors.neutral40
        )
    }
}
