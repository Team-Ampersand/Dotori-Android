package com.msg.presentation.view.notice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.ArrowLeftIcon
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.PenIcon
import com.dotori.dotori_components.theme.TrashCanIcon
import com.dotori.dotori_components.theme.White

@Composable
fun NoticeDetailHeader(
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = White)
            .padding(start = 16.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)
    ) {
        ArrowLeftIcon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onBackClick
            ),
            tint = DotoriTheme.colors.neutral20
        )
        Spacer(modifier = Modifier.weight(1f))
        PenIcon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onEditClick
            ),
            tint = DotoriTheme.colors.neutral10
        )
        Spacer(modifier = Modifier.width(16.dp))
        TrashCanIcon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onDeleteClick
            ),
            tint = DotoriTheme.colors.error
        )
    }
}
