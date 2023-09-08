package com.msg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.auth.LoginRequestModel
import com.msg.domain.usecase.auth.LoginUseCase
import com.msg.domain.usecase.auth.SaveTokenUseCase
import com.msg.presentation.exception.exceptionHandling
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
): ViewModel() {
    fun login(loginRequestModel: LoginRequestModel) {
        viewModelScope.launch {
            loginUseCase(loginRequestModel)
                .onSuccess {
                    saveTokenUseCase(
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiresAt = it.expiresAt
                    )
                }
                .onFailure {
                    it.exceptionHandling(
                        badRequestAction = {},
                        notFoundAction = {}
                    )
                }
        }
    }
}