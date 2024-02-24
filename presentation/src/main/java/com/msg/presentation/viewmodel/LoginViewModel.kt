package com.msg.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.auth.LoginRequestModel
import com.msg.domain.model.auth.LoginResponseModel
import com.msg.domain.usecase.auth.LoginUseCase
import com.msg.domain.usecase.auth.SaveRoleUseCase
import com.msg.domain.usecase.auth.SaveTokenUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val saveRoleUseCase: SaveRoleUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow<Event<LoginResponseModel>>(Event.Loading)
    val loginState = _loginState.asStateFlow()

    fun login(loginRequestModel: LoginRequestModel) = viewModelScope.launch {
        _loginState.value = Event.Loading
        loginUseCase(loginRequestModel)
            .onSuccess {
                _loginState.value = Event.Success(it)
                saveTokenUseCase(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken,
                    expiresAt = it.expiresAt
                )
                saveRoleUseCase(it.roles.toString())
                Log.d("failure", "success")
            }
            .onFailure {
                Log.d("failure", it.message.toString())
                it.exceptionHandling(
                    badRequestAction = { _loginState.value = Event.BadRequest },
                    notFoundAction = { _loginState.value = Event.NotFound }
                )
            }
    }
}