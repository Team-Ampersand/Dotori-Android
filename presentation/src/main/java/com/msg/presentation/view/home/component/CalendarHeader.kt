package com.msg.presentation.view.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
    title: LocalDate,
    onLeftClicked: () -> Unit,
    onRightClicked: () -> Unit
) {
    val dayOfWeek = title.dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN)
    val formattedDate = DateTimeFormatter.ofPattern("yyyy.MM.dd").format(title)

    Row(
        modifier = modifier.background(color = DotoriTheme.colors.cardBackground),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            modifier = Modifier.clickable { onLeftClicked() },
            painter = painterResource(R.drawable.ic_left_icon),
            contentDescription = "previous"
        )
        Text(
            text = "$formattedDate ($dayOfWeek)",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
        Image(
            modifier = Modifier.clickable { onRightClicked() },
            painter = painterResource(R.drawable.ic_right_icon),
            contentDescription = "next"
        )
    }
}

@Preview
@Composable
fun CalendarHeaderPre() {
    var date by remember { mutableStateOf(LocalDate.now()) }

    CalendarHeader(
        title = date,
        onLeftClicked = { date = date.minusDays(1) },
        onRightClicked = { date = date.plusDays(1) }
    )
}