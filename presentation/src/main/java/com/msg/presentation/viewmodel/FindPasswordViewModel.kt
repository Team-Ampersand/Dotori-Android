package com.msg.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.email.EmailVerifyRequestModel
import com.msg.domain.model.email.SendEmailRequestModel
import com.msg.domain.model.auth.ChangePasswordRequestModel
import com.msg.domain.usecase.email.EmailVerifyUseCase
import com.msg.domain.usecase.email.SendPasswordEmailUseCase
import com.msg.domain.usecase.auth.ChangePasswordUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor(
    private val sendPasswordEmailUseCase: SendPasswordEmailUseCase,
    private val emailVerifyUseCase: EmailVerifyUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {
    private val _sendPasswordEmailState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val sendPasswordEmailState = _sendPasswordEmailState.asStateFlow()

    private val _emailVerifyState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val emailVerifyState = _emailVerifyState.asStateFlow()

    private val _changePasswordState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val changePasswordState = _changePasswordState.asStateFlow()

    val email = mutableStateOf("")
    val password = mutableStateOf("")

    fun sendPasswordEmail(body: SendEmailRequestModel) = viewModelScope.launch {
        sendPasswordEmailUseCase(body)
            .onSuccess {
                _sendPasswordEmailState.value = Event.Success()
            }.onFailure {
                it.exceptionHandling(
                    conflictAction = { Event.Conflict }
                )
            }
    }

    fun emailVerify(body: EmailVerifyRequestModel) = viewModelScope.launch {
        emailVerifyUseCase(body)
            .onSuccess {
                _emailVerifyState.value = Event.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    notFoundAction = { Event.NotFound }
                )
            }
    }

    fun initEmailVerify() {
        _emailVerifyState.value = Event.Loading
    }

    fun changePassword(body: ChangePasswordRequestModel) = viewModelScope.launch {
        changePasswordUseCase(body)
            .onSuccess {
                _changePasswordState.value = Event.Success()
            }.onFailure {
                it.exceptionHandling(
                    unauthorizedAction = { Event.Unauthorized }
                )
            }
    }

    fun initChangePassword() {
        _changePasswordState.value = Event.Loading
    }
}