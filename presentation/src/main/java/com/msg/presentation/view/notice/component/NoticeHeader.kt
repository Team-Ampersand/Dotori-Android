package com.msg.presentation.view.notice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.theme.DotoriTheme

@Composable
fun NoticeHeader(
    onEditClick: () -> Unit,
    onWriteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(DotoriTheme.colors.cardBackground)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "전체",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )
        Spacer(modifier = Modifier.weight(1f))
        DotoriButton(
            modifier = Modifier.size(width = 52.dp, height = 32.dp),
            text = "편집",
            colors = Color.Transparent,
            shape = RoundedCornerShape(50.dp),
            onClick = onEditClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        DotoriButton(
            modifier = Modifier.size(width = 68.dp, height = 32.dp),
            text = "\u002b 작성",
            shape = RoundedCornerShape(50.dp),
            onClick = onWriteClick
        )
    }
}
