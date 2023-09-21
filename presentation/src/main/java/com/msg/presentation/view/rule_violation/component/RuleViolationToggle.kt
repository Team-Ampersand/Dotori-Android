package com.msg.presentation.view.rule_violation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.utils.RoleViolateType
import com.dotori.dotori_components.components.utils.roleTypeMap
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.XMarkIcon2

@Composable
fun RuleViolationToggle(
    modifier: Modifier = Modifier,
    violateText: String
) {
    val violateType = enumValueOf<RoleViolateType>(violateText)

    Row(
        modifier = modifier
            .height(30.dp)
            .background(
                color = DotoriTheme.colors.background,
                shape = CircleShape
            )
            .padding(horizontal = 12.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${roleTypeMap[violateType]}",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
        XMarkIcon2(
            modifier = Modifier.fillMaxHeight(),
            tint = DotoriTheme.colors.neutral20
        )
    }

}

@Preview
@Composable
fun RuleViolationTogglePreview() {
    RuleViolationToggle(violateText = "FIREARMS")
}