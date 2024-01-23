package com.msg.presentation.view.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.drawer.content.DrawerViewHeader
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.ArrowLeft2Icon
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.LockIcon
import com.dotori.dotori_components.theme.PersonIcon
import com.dotori.dotori_components.theme.XMarkIcon
import com.msg.domain.model.auth.LoginRequestModel
import com.msg.domain.model.auth.LoginResponseModel
import com.msg.presentation.viewmodel.LoginViewModel
import com.msg.presentation.viewmodel.util.Event

@SuppressLint("RememberReturnType")
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToMain: () -> Unit
) {
    var idText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var isIdClicked by remember { mutableStateOf(false) }
    var isPasswordClicked by remember { mutableStateOf(false) }

    val loginUiState by loginViewModel.loginState.collectAsState()

    when (loginUiState) {
        is Event.Success -> navigateToMain()
        else -> {}
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background)
            .padding(horizontal = 20.dp)
    ) {
        Box(modifier = Modifier.padding(top = 16.dp)) {
            ArrowLeft2Icon(contentDescription = "ArrowLeftIcon")
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                text = "로그인",
                style = DotoriTheme.typography.subTitle2,
                color = DotoriTheme.colors.neutral10,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) { DrawerViewHeader() }
        Spacer(modifier = Modifier.height(8.dp))
        DotoriTextField(
            modifier = Modifier.onFocusChanged { isIdClicked = it.isFocused },
            value = idText,
            placeholder = "아이디",
            onValueChange = { idText = it },
            leadingIcon = {
                PersonIcon(
                    tint = if (isIdClicked) DotoriTheme.colors.neutral10
                    else DotoriTheme.colors.neutral30
                )
            },
            trailingIcon = { if (isIdClicked && idText.isNotBlank()) XMarkIcon(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { idText = "" }
                )
            ) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DotoriTextField(
            modifier = Modifier.onFocusChanged { isPasswordClicked = it.isFocused },
            value = passwordText,
            placeholder = "비밀번호",
            onValueChange = { passwordText = it },
            leadingIcon = {
                LockIcon(
                    tint = if (isPasswordClicked) DotoriTheme.colors.neutral10
                    else DotoriTheme.colors.neutral30
                )
            },
            trailingIcon = { if (isPasswordClicked && passwordText.isNotBlank()) XMarkIcon(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { passwordText = "" }
                )
            ) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {},
            text = "비밀번호 찾기",
            style = DotoriTheme.typography.body2,
            color = DotoriTheme.colors.neutral20,
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(32.dp))
        DotoriButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "로그인"
        ) {
            loginViewModel.login(
                LoginRequestModel(
                    email = idText,
                    password = passwordText
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Dotori가 처음이라면? ",
                style = DotoriTheme.typography.body2,
                color = DotoriTheme.colors.neutral20,
            )
            Text(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {},
                text = "회원가입",
                style = DotoriTheme.typography.body2,
                color = DotoriTheme.colors.primary10,
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen {}
}