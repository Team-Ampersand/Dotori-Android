package com.msg.presentation.view.rule_violation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.DownloadIcon
import com.dotori.dotori_components.theme.FilterIcon
import com.dotori.dotori_components.theme.Transparent

@Composable
fun RuleViolationHeader(
    modifier: Modifier = Modifier,
    onWriteRuleViolationClick: () -> Unit,
    onDownloadClick: () -> Unit,
    onFilterClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(DotoriTheme.colors.cardBackground)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "규정위반",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            modifier = modifier.size(120.dp, 32.dp),
            border = BorderStroke(1.dp, DotoriTheme.colors.error),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Transparent,
                contentColor = DotoriTheme.colors.error
            ),
            shape = RoundedCornerShape(8.dp),
            onClick = onWriteRuleViolationClick
        ) {
            Text(
                text = "규정 위반 기록",
                style = DotoriTheme.typography.smallTitle
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        DownloadIcon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onDownloadClick
            ),
            tint = DotoriTheme.colors.neutral20
        )
        Spacer(modifier = Modifier.width(20.dp))
        FilterIcon(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onFilterClick
            ),
            tint = DotoriTheme.colors.neutral20
        )
    }
}

@Preview
@Composable
fun RuleViolationHeaderPreview() {
    RuleViolationHeader(
        onWriteRuleViolationClick = { /*TODO*/ },
        onDownloadClick = { /*TODO*/ },
        onFilterClick = { /*TODO*/ }
    )
}