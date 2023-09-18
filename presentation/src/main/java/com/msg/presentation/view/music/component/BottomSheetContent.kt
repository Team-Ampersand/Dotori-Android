package com.msg.presentation.view.music.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.calendar.DotoriCalendar
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.ShortcutsIcon
import com.dotori.dotori_components.theme.TrashCanIcon
import java.time.LocalDate

@Composable
fun BottomSheetContent(
    bottomSheetType: BottomSheetType,
    onLinkClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDaySelected: (LocalDate) -> Unit
) {
    when (bottomSheetType) {
        BottomSheetType.Option -> {
            MusicOption(
                onLinkClick = onLinkClick,
                onDeleteClick = onDeleteClick
            )
        }

        BottomSheetType.Calendar -> {
            DotoriCalendar(onDaySelected = onDaySelected)
        }
    }
}

@Composable
fun MusicOption(
    onLinkClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onLinkClick
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShortcutsIcon(tint = DotoriTheme.colors.neutral20)
            Text(
                text = "바로가기",
                style = DotoriTheme.typography.body
            )
        }
        Row(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onDeleteClick
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrashCanIcon(tint = DotoriTheme.colors.neutral20)
            Text(
                text = "기상음악 삭제",
                style = DotoriTheme.typography.body
            )
        }
    }
}