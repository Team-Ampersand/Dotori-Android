package com.msg.presentation.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.drawer.content.DrawerViewHeader
import com.dotori.dotori_components.theme.ArrowLeft2Icon
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.gauthsignin.component.GAuthButton
import com.msg.gauthsignin.component.utils.Types

@Composable
fun GAuthLoginScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background)
    ) {
        Box(modifier = Modifier.padding(top = 16.dp)) {
            ArrowLeft2Icon(
                contentDescription = "ArrowLeftIcon",
                modifier = modifier.padding(start = 20.dp)
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                text = "로그인",
                style = DotoriTheme.typography.subTitle2,
                color = DotoriTheme.colors.neutral10,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) { DrawerViewHeader() }
        Text(
            modifier = modifier
                .size(width = 208.dp, height = 34.dp)
                .padding(start = 24.dp),
            text = "광주소프트웨어마이스터고 기숙사 관리 시스템, DOTORI",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
        Spacer(modifier = modifier.height(504.dp))
        GAuthButton(
            style = Types.Style.DEFAULT,
            actionType = Types.ActionType.SIGNIN,
            colors = Types.Colors.COLORED,
            horizontalPaddingValue = 70.dp,
        ) {

        }
    }
}

@Preview
@Composable
fun GAuthLoginScreenPreview() {
    GAuthLoginScreen()
}