package com.msg.presentation.view.notice.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NoticeDetailTitle(
    modifier: Modifier,
    title: String,
    createdDate: LocalDateTime,
    role: String
) {
    val (roleColor, roleTitle) = when (role) {
        "ROLE_MEMBER" -> DotoriTheme.colors.neutral20 to "학생"
        "ROLE_COUNCILLOR" -> DotoriTheme.colors.subRed to "기숙사자치위원회"
        "ROLE_DEVELOPER" -> DotoriTheme.colors.primary10 to "도토리"
        else -> DotoriTheme.colors.subYellow to "사감선생님"
    }

    val formattedDate = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일").format(createdDate)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(color = roleColor, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = roleTitle,
                style = DotoriTheme.typography.h4,
                color = DotoriTheme.colors.neutral10
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = formattedDate,
                style = DotoriTheme.typography.body2,
                color = DotoriTheme.colors.neutral20
            )
        }
        Text(
            text = title,
            style = DotoriTheme.typography.h3,
            color = DotoriTheme.colors.neutral10
        )
    }
}
