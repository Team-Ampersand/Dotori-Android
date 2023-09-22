package com.msg.presentation.view.notice.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.White

@Composable
fun NoticeDialogContent(
    title: String,
    content: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )
        Text(
            text = content,
            minLines = 2,
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "취소",
                textColor = White,
                paddingValues = PaddingValues(vertical = 12.dp),
                onClick = onDismiss
            )
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "확인",
                colors = Color.Transparent,
                paddingValues = PaddingValues(vertical = 12.dp),
                onClick = onConfirm
            )
        }
    }
}
