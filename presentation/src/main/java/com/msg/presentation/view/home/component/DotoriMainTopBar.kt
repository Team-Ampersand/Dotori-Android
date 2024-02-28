package com.msg.presentation.view.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.toggle.DotoriThemeSwitchButton
import com.dotori.dotori_components.components.utils.Theme
import com.dotori.dotori_components.theme.DotoriText
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.HamburgerIcon
import com.dotori.dotori_components.theme.White
import com.example.dus.R

@Composable
fun DotoriMainTopBar(
    isDark: Boolean,
    onSwitchClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HamburgerIcon()
        Spacer(modifier = modifier.width(16.dp))
        DotoriText()

        Spacer(modifier = modifier.weight(1f))

        DotoriThemeSwitchButton(
            isDark = isDark,
            onSwitchClick = onSwitchClick
        )
        Spacer(modifier = modifier.width(16.dp))
        Image(
            painter = if (DotoriTheme.dotoriTheme == Theme.LIGHT) painterResource(id = R.drawable.ic_profile_light)
            else painterResource(id = R.drawable.ic_profile_dark),
            contentDescription = "profile image",
            modifier = modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
fun DotoriMainTopBarPre() {
    DotoriMainTopBar(
        isDark = DotoriTheme.isSystemIsDarkTheme(),
        onSwitchClick = { }
    )
}