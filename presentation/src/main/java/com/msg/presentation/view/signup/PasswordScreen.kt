package com.msg.presentation.view.signup

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.ArrowLeft2Icon
import com.dotori.dotori_components.theme.DotoriLogoIcon
import com.dotori.dotori_components.theme.DotoriText
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.XMarkIcon
import com.msg.presentation.view.signup.component.SignUpDatIndicator

@Composable
fun PasswordScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit
) {
    var passwordText by remember { mutableStateOf("") }
    var checkPasswordText by remember { mutableStateOf("") }
    var isPasswordClicked by remember { mutableStateOf(false) }
    var isCheckPasswordClicked by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DotoriTheme.colors.background)
            .padding(horizontal = 20.dp)
    ) {
        Box(modifier = Modifier.padding(top = 16.dp)) {
            ArrowLeft2Icon(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { navigateToBack() },
                contentDescription = "ArrowLeftIcon"
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                text = "회원가입",
                style = DotoriTheme.typography.subTitle2,
                color = DotoriTheme.colors.neutral10,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 18.5.dp, bottom = 18.5.dp)
                    .background(DotoriTheme.colors.background),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DotoriLogoIcon()
                DotoriText()
            }
            SignUpDatIndicator(index = 3)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "비밀번호",
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        DotoriTextField(
            modifier = Modifier.onFocusChanged { isPasswordClicked = it.isFocused },
            value = passwordText,
            placeholder = "비밀번호",
            onValueChange = { passwordText = it },
            trailingIcon = {
                if (isPasswordClicked && passwordText.isNotBlank()) XMarkIcon(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { passwordText = "" }
                    )
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "비밀번호 확인",
            style = DotoriTheme.typography.smallTitle,
            color = DotoriTheme.colors.neutral10,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(8.dp))
        DotoriTextField(
            modifier = Modifier.onFocusChanged { isCheckPasswordClicked = it.isFocused },
            value = checkPasswordText,
            placeholder = "비밀번호 확인",
            onValueChange = { checkPasswordText = it },
            trailingIcon = {
                if (isCheckPasswordClicked && checkPasswordText.isNotBlank()) XMarkIcon(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { checkPasswordText = "" }
                    )
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))
        DotoriButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            text = "회원가입"
        ) {
            navigateToLogin()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "이미 회원이라면? ",
                style = DotoriTheme.typography.body2,
                color = DotoriTheme.colors.neutral20,
            )
            Text(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navigateToLogin()
                },
                text = "로그인",
                style = DotoriTheme.typography.body2,
                color = DotoriTheme.colors.primary10,
            )
        }
    }
}
