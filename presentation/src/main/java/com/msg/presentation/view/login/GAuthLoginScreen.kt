package com.msg.presentation.view.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.msg.presentation.R
import androidx.compose.ui.res.stringResource

@Composable
fun GAuthLoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background)
            .padding(bottom = 60.dp)
    ) {
        Box(modifier = Modifier.padding(top = 16.dp)) {
            ArrowLeft2Icon(
                contentDescription = "ArrowLeftIcon",
                modifier = Modifier.padding(start = 20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                text = stringResource(R.string.login_text),
                style = DotoriTheme.typography.subTitle2,
                color = DotoriTheme.colors.neutral10,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) { DrawerViewHeader() }
        Text(
            modifier = Modifier
                .size(width = 208.dp, height = 34.dp)
                .padding(start = 24.dp),
            text = stringResource(R.string.login_page_dotori_description),
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
        Spacer(modifier = Modifier.weight(1f))
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