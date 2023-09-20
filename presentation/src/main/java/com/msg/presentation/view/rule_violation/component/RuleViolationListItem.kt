package com.msg.presentation.view.rule_violation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme

@Composable
fun RuleViolationListItem(
    modifier: Modifier = Modifier,
    text: String,
    onClicked: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isClicked by interactionSource.collectIsPressedAsState()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isClicked) DotoriTheme.colors.primary20 else DotoriTheme.colors.cardBackground,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClicked
            )
    ) {
        Text(
            text = text,
            style = DotoriTheme.typography.subTitle2,
            color = if (isClicked) DotoriTheme.colors.primary10 else DotoriTheme.colors.neutral30
        )
    }
}

@Preview
@Composable
fun RuleViolationListItemPreview() {
    RuleViolationListItem(text = "금지 물품 반입") {}
}