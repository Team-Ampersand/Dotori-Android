package com.msg.presentation.view.student_info.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriSegmentedButtons
import com.dotori.dotori_components.theme.DotoriTheme

@Composable
fun StudentInfoSegmentButton(
    title: String,
    list: List<String>,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10
        )
        DotoriSegmentedButtons(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            sectionNames = list,
            rowPadding = 4.dp,
            textPadding = 10.dp,
            outRoundedCornerShape = 8.dp,
            innerRoundedCornerShape = 4.dp
        ) {}
    }
}