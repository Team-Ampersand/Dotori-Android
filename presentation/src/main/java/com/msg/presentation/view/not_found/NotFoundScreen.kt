package com.msg.presentation.view.not_found

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.R

@Composable
fun NotFoundScreen(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit
) {
    val imageRes = if (DotoriTheme.isSystemIsDarkTheme()) {
        R.drawable.ic_404_dark
    } else {
        R.drawable.ic_404_light
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "404 image"
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "요청하신 페이지를 찾을수 없습니다.",
            style = DotoriTheme.typography.subTitle2,
            color = DotoriTheme.colors.neutral10
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "입력한 주소가 맞는지 다시 한번 확인해 주시기를 바랍니다.",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20
        )
        Spacer(modifier = Modifier.height(24.dp))
        DotoriButton(
            modifier = Modifier.size(118.dp, 48.dp),
            text = "홈화면",
            onClick = navigateToMain
        )
    }
}

@Preview
@Composable
fun NotFoundScreenPreview() {
    NotFoundScreen {}
}