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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.ArrowLeft2Icon
import com.dotori.dotori_components.theme.DotoriLogoIcon
import com.dotori.dotori_components.theme.DotoriText
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.XMarkIcon
import com.msg.domain.model.email.EmailVerifyRequestModel
import com.msg.domain.model.email.SendEmailRequestModel
import com.msg.presentation.view.signup.component.SignUpDatIndicator
import com.msg.presentation.viewmodel.util.Event
import com.msg.presentation.viewmodel.util.SignUpViewModelProvider

@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner,
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToPassword: () -> Unit
) {
    SignUpViewModelProvider(viewModelStoreOwner = viewModelStoreOwner) { signUpViewModel ->
        var emailText by remember { mutableStateOf("") }
        var numberText by remember { mutableStateOf("") }
        var isEmailClicked by remember { mutableStateOf(false) }
        var isNumberClicked by remember { mutableStateOf(false) }

        val sendEmailState by signUpViewModel.sendEmailState.collectAsState()
        val emailVerifyState by signUpViewModel.emailVerifyState.collectAsState()

        when (emailVerifyState) {
            is Event.Success -> {
                navigateToPassword()
                signUpViewModel.initEmailVerify()
            }
            else -> {}
        }

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
                SignUpDatIndicator(index = 2)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "이메일",
                style = DotoriTheme.typography.smallTitle,
                color = DotoriTheme.colors.neutral10,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DotoriTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .onFocusChanged { isEmailClicked = it.isFocused },
                    value = emailText,
                    placeholder = "이메일",
                    onValueChange = { emailText = it },
                    trailingIcon = {
                        if (isEmailClicked && emailText.isNotBlank()) XMarkIcon(
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { emailText = "" }
                            )
                        )
                    }
                )
                DotoriButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    text = "인증하기"
                ) {
                    signUpViewModel.email.value = emailText
                    signUpViewModel.sendEmail(SendEmailRequestModel(signUpViewModel.email.value))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "인증번호",
                style = DotoriTheme.typography.smallTitle,
                color = DotoriTheme.colors.neutral10,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            DotoriTextField(
                modifier = Modifier.onFocusChanged { isNumberClicked = it.isFocused },
                value = numberText,
                placeholder = "인증번호",
                onValueChange = { numberText = it },
                trailingIcon = {
                    if (isNumberClicked && numberText.isNotBlank()) XMarkIcon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { numberText = "" }
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            DotoriButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                text = "다음"
            ) {
                signUpViewModel.emailVerify(EmailVerifyRequestModel(numberText))
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
}
