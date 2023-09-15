package com.msg.presentation.view.student_info.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.XMarkIcon

@Composable
fun StudentInfoTextField(
    title: String,
    content: String
) {
    var text by remember { mutableStateOf(content) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10
        )
        DotoriTextField(
            value = text,
            placeholder = "",
            onValueChange = { text = it },
            trailingIcon = { if (text.isNotBlank()) XMarkIcon(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { text = "" }
                )
            ) }
        )
    }
}