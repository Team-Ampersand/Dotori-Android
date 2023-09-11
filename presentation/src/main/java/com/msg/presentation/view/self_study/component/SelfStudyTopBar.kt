package com.msg.presentation.view.self_study.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.FilterIcon

@Composable
fun SelfStudyTopBar(onFilterIconClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(DotoriTheme.colors.cardBackground)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "자습신청",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )

        FilterIcon(
            modifier = Modifier.clickable { onFilterIconClick() },
            tint = DotoriTheme.colors.neutral20
        )
    }
}