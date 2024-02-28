package com.msg.presentation.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.card.DotoriMainCard
import com.dotori.dotori_components.components.utils.Types
import com.dotori.dotori_components.components.watch.DotoriWatch
import com.dotori.dotori_components.theme.DotoriTheme
import com.msg.presentation.view.home.component.Cafeteria
import com.msg.presentation.view.home.component.DotoriMainTopBar
import com.msg.presentation.view.util.updateDotoriTheme
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val currentTime = remember {
        mutableStateOf(Date())
    }
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1_000)
            currentTime.value = Date()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background)
    ) {
        DotoriMainTopBar(
            isDark = DotoriTheme.isSystemIsDarkTheme(),
            onSwitchClick = { DotoriTheme.updateDotoriTheme() }
        )

        Spacer(modifier = modifier.height(12.dp))
        Column(
            modifier = modifier
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            DotoriWatch(
                time = currentTime.value
            )
            Spacer(modifier = modifier.height(12.dp))
            DotoriMainCard(
                count = 10,
                limit = 50,
                role = "ROLE_MEMBER",
                mode = Types.CardType.SELF_STUDY,
                arrowClick = { /*TODO*/ },
                settingClick = { /*TODO*/ },
                refreshClick = { /*TODO*/ },
                buttonClick = { /*TODO*/ }
            )
            Spacer(modifier = modifier.height(12.dp))
            DotoriMainCard(
                count = 0,
                limit = 50,
                role = "ROLE_DEVELOPER",
                mode = Types.CardType.MASSAGE_CHAIR,
                arrowClick = { /*TODO*/ },
                settingClick = { /*TODO*/ },
                refreshClick = { /*TODO*/ },
                buttonClick = { /*TODO*/ }
            )
            Spacer(modifier = modifier.height(12.dp))
            Cafeteria()
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
