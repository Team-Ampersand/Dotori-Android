package com.msg.presentation.view.music.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.toggle.DotoriThemeSwitchButton
import com.dotori.dotori_components.theme.DotoriText
import com.dotori.dotori_components.theme.White

@Composable
fun DotoriTopBar(
    isDark: Boolean,
    onSwitchClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DotoriText()
        DotoriThemeSwitchButton(
            isDark = isDark,
            onSwitchClick = onSwitchClick
        )
    }
}