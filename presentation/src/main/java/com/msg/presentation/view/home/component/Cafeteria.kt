package com.msg.presentation.view.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriSegmentedButtons
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.R
import java.time.LocalDate

@Composable
fun Cafeteria(
    modifier: Modifier = Modifier
) {
    var date by remember { mutableStateOf(LocalDate.now()) }
    var list = mutableListOf("조식", "중식", "석식")

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(160 / 281f),
        color = DotoriTheme.colors.cardBackground,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.cafeteria),
                    style = DotoriTheme.typography.subTitle2,
                    color = DotoriTheme.colors.neutral10
                )
                Spacer(modifier = modifier.weight(1f))
                CalendarHeader(
                    title = date,
                    onLeftClicked = { date.minusDays(1) },
                    onRightClicked = { date.plusDays(1) }
                )
            }
            DotoriSegmentedButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                textPadding = 13.dp,
                rowPadding = 4.dp,
                outRoundedCornerShape = 4.dp,
                innerRoundedCornerShape = 8.dp,
                sectionNames = list,
                onSwitchClick = {}
            )
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(271 / 398f),
                color = DotoriTheme.colors.neutral50,
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "급식이 없습니다.",
                        style = DotoriTheme.typography.subTitle2,
                        color = DotoriTheme.colors.neutral10
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CafeteriaPre() {
    Cafeteria()
}