package com.msg.presentation.view.rule_violation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.CalendarIcon
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.XMarkIcon

@Composable
fun RuleViolationTextItem(
    title: String = "",
    content: String = "",
    contentList: List<String> = emptyList(),
    textColor: Color = DotoriTheme.colors.neutral10,
    icon: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (title.isNotBlank()) {
            Text(
                text = title,
                style = DotoriTheme.typography.smallTitle,
                color = DotoriTheme.colors.neutral10
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = DotoriTheme.colors.neutral50,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 14.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (content.isNotBlank()) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = content,
                    style = DotoriTheme.typography.body,
                    color = textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                icon()
            } else {
                contentList.forEachIndexed { index, string ->
                    Row {
                        Text(
                            text = if (index != contentList.lastIndex) "$string, " else string,
                            style = DotoriTheme.typography.body,
                            color = textColor,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                icon()
            }
        }
    }
}

@Preview
@Composable
fun RuleViolationTextItemPreview() {
    Column {
        RuleViolationTextItem(
            title = "학생",
            content = "선민재, 박영재, 강경민, 조재영",
            icon = { XMarkIcon() }
        )
        Spacer(modifier = Modifier.height(15.dp))
        RuleViolationTextItem(
            content = "2023년 8월 12일 (오늘)",
            icon = { CalendarIcon() }
        )
        Spacer(modifier = Modifier.height(15.dp))
        RuleViolationTextItem(
            contentList = listOf("선민재", "박영재", "강경민", "조재영"),
            icon = { XMarkIcon() }
        )
    }
}