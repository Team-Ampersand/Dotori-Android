package com.msg.presentation.view.student_info.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.Transparent

@Composable
fun StudentInfoDialogContent(
    isBaned: Boolean,
    name: String,
    onSubmit: () -> Unit,
    onCancel: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = if (isBaned) "자습 금지 해제" else "자습 금지",
            style = DotoriTheme.typography.subTitle1,
            color = DotoriTheme.colors.neutral10
        )
        Text(
            modifier = Modifier.height(30.dp), // 미리 2줄 들어갈 정도의 height를 지정 (디자인 참고)
            text = if (isBaned) "$name 학생의 자습 금지를 해제하시겠습니까?"
            else "$name 학생을 자습 금지하시겠습니까?",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20,
            maxLines = 2
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "취소",
                colors = Transparent,
                paddingValues = PaddingValues(6.dp),
                onClick = onCancel
            )
            DotoriButton(
                modifier = Modifier.weight(1f),
                text = "확인",
                paddingValues = PaddingValues(6.dp),
                onClick = onSubmit
            )
        }
    }
}