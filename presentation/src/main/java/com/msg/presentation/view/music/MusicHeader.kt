package com.msg.presentation.view.music

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.CalendarIcon
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.PlusIcon

@Composable
fun MusicHeader(
    modifier: Modifier = Modifier,
    onCalendarClick: () -> Unit,
    onMusicClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DotoriTheme.colors.cardBackground)
            .padding(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = "기상음악 신청",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )
        Spacer(modifier = Modifier.weight(1f))
        CalendarIcon(
            modifier = Modifier.clickable { onCalendarClick() },
            tint = DotoriTheme.colors.neutral20
        )
        Spacer(modifier = Modifier.width(12.dp))
        PlusIcon(
            modifier = Modifier.clickable { onMusicClick() },
            tint = DotoriTheme.colors.neutral20,
        )
    }
}