package com.msg.presentation.view.music

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.WarningIcon

@Composable
fun MusicDialogContent(
    url : String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit
) {

    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "음악 신청",
                style = DotoriTheme.typography.subTitle1,
                color = DotoriTheme.colors.neutral10
            )
            WarningIcon()
        }
        Spacer(modifier = Modifier.height(16.dp))
        DotoriTextField(
            value = url,
            placeholder = "URL을 입력해주세요.",
            onValueChange = onValueChange
        )
        Spacer(modifier = Modifier.height(8.dp))
        DotoriButton(
            modifier = Modifier.fillMaxWidth(),
            text = "신청하기",
            paddingValues = PaddingValues(6.dp),
            onClick = onSubmit
        )
    }
}