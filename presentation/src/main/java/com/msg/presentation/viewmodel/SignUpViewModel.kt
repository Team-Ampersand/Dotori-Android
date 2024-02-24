package com.msg.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.auth.GenderModel
import com.msg.domain.model.auth.SignUpRequestModel
import com.msg.domain.model.email.EmailVerifyRequestModel
import com.msg.domain.model.email.SendEmailRequestModel
import com.msg.domain.usecase.auth.SignUpUseCase
import com.msg.domain.usecase.email.EmailVerifyUseCase
import com.msg.domain.usecase.email.SendEmailUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val sendEmailUseCase: SendEmailUseCase,
    private val emailVerifyUseCase: EmailVerifyUseCase
) : ViewModel() {
    private val _signUpState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val signUpState = _signUpState.asStateFlow()

    private val _sendEmailState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val sendEmailState = _sendEmailState.asStateFlow()

    private val _emailVerifyState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val emailVerifyState = _emailVerifyState.asStateFlow()

    val memberName = mutableStateOf("")
    val stuNum = mutableStateOf("")
    val password = mutableStateOf("")
    val email = mutableStateOf("")
    val gender = mutableStateOf(GenderModel.MAN)


    fun signUp(body: SignUpRequestModel) = viewModelScope.launch {
        _signUpState.value = Event.Loading
        signUpUseCase(body)
            .onSuccess {
                _signUpState.value = Event.Success()
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = { Event.BadRequest },
                    conflictAction = { Event.Conflict }
                )
            }
    }

    fun initSignUp() {
        _signUpState.value = Event.Loading
    }

    fun sendEmail(body: SendEmailRequestModel) = viewModelScope.launch {
        sendEmailUseCase(body)
            .onSuccess {
                _sendEmailState.value = Event.Success()
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
}