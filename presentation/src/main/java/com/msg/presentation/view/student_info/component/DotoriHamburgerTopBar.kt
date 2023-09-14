package com.msg.presentation.view.student_info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.toggle.DotoriThemeSwitchButton
import com.dotori.dotori_components.theme.DotoriText
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.HamburgerIcon

@Composable
fun DotoriHamburgerTopBar(
    modifier: Modifier = Modifier,
    onHamburgerClick: () -> Unit,
    onSwitchClick: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DotoriTheme.colors.cardBackground)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HamburgerIcon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onHamburgerClick
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        DotoriText()
        Spacer(modifier = Modifier.weight(1f))
        DotoriThemeSwitchButton(onSwitchClick = onSwitchClick)
    }
}

@Preview
@Composable
fun DotoriHamburgerTopBarPreview() {
    DotoriHamburgerTopBar(
        onHamburgerClick = { /*TODO*/ },
        onSwitchClick = { /*TODO*/ }
    )
}