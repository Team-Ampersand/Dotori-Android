package com.msg.presentation.view.signup

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import com.dotori.dotori_components.components.button.DotoriButton
import com.dotori.dotori_components.components.text_field.DotoriTextField
import com.dotori.dotori_components.theme.ArrowLeft2Icon
import com.dotori.dotori_components.theme.DotoriLogoIcon
import com.dotori.dotori_components.theme.DotoriText
import com.dotori.dotori_components.theme.DotoriTheme
import com.dotori.dotori_components.theme.EyeCloseIcon
import com.dotori.dotori_components.theme.EyeOpenIcon
import com.dotori.dotori_components.theme.XMarkIcon
import com.msg.domain.model.auth.SignUpRequestModel
import com.msg.presentation.view.signup.component.SignUpDatIndicator
import com.msg.presentation.view.util.isStrongPassword
import com.msg.presentation.viewmodel.util.Event
import com.msg.presentation.viewmodel.util.SignUpViewModelProvider

@Composable
fun PasswordScreen(
    modifier: Modifier = Modifier,
    viewModelStoreOwner: ViewModelStoreOwner,
    navigateToBack: () -> Unit,
    navigateToLogin: () -> Unit
) {
    SignUpViewModelProvider(viewModelStoreOwner = viewModelStoreOwner) { signUpViewModel ->
        val context = LocalContext.current
        var passwordText by remember { mutableStateOf("") }
        var checkPasswordText by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        val passwordVisualTransformation = remember(isPasswordVisible) {
            if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        }
        var isCheckPasswordVisible by remember { mutableStateOf(false) }
        val checkPasswordVisualTransformation = remember(isCheckPasswordVisible) {
            if (isCheckPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        }

        val signUpState by signUpViewModel.signUpState.collectAsState()

        when (signUpState) {
            is Event.Success -> {
                navigateToLogin()
                signUpViewModel.initSignUp()
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
                value = passwordText,
                placeholder = "비밀번호",
                onValueChange = { passwordText = it },
                trailingIcon = {
                    if (isPasswordVisible) EyeCloseIcon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { isPasswordVisible = !isPasswordVisible }
                        )
                    ) else {
                        EyeOpenIcon(
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { isPasswordVisible = !isPasswordVisible }
                            )
                        )
                    }
                },
                visualTransformation = passwordVisualTransformation
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
                value = checkPasswordText,
                placeholder = "비밀번호 확인",
                onValueChange = { checkPasswordText = it },
                trailingIcon = {
                    if (isCheckPasswordVisible) EyeCloseIcon(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { isCheckPasswordVisible = !isCheckPasswordVisible }
                        )
                    ) else {
                        EyeOpenIcon(
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { isCheckPasswordVisible = !isCheckPasswordVisible }
                            )
                        )
                    }
                },
                visualTransformation = checkPasswordVisualTransformation
            )
            Spacer(modifier = Modifier.height(24.dp))
            DotoriButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                text = "회원가입"
            ) {
                if (passwordText != checkPasswordText) {
                    Toast.makeText(context, "비밀번호가 서로 일치하지 않습니다..", Toast.LENGTH_SHORT).show()
                } else if (!isStrongPassword(passwordText)) {
                    Toast.makeText(context, "비밀번호 형식에 맞게 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    signUpViewModel.password.value = passwordText
                    signUpViewModel.signUp(
                        SignUpRequestModel(
                            memberName = signUpViewModel.memberName.value,
                            stuNum = signUpViewModel.stuNum.value,
                            password = signUpViewModel.password.value,
                            email = signUpViewModel.email.value,
                            gender = signUpViewModel.gender.value
                        )
                    )
                }
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
