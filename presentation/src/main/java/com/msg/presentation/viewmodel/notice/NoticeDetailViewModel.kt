package com.msg.presentation.viewmodel.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.notice.response.NoticeDetailResponseModel
import com.msg.domain.usecase.auth.GetRoleUseCase
import com.msg.domain.usecase.notice.DeleteNoticeByIdUseCase
import com.msg.domain.usecase.notice.GetNoticeDetailUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeDetailViewModel @Inject constructor(
    private val getRoleUseCase: GetRoleUseCase,
    private val deleteNoticeByIdUseCase: DeleteNoticeByIdUseCase,
    private val getNoticeDetailUseCase: GetNoticeDetailUseCase
) : ViewModel() {
    private val _roleUiState = MutableStateFlow<Event<String>>(Event.Loading)
    val roleUiState = _roleUiState.asStateFlow()

    private val _noticeUiState = MutableStateFlow<Event<NoticeDetailResponseModel>>(Event.Loading)
    val noticeUiState = _noticeUiState.asStateFlow()

    private val _deleteUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val deleteUiState = _deleteUiState.asStateFlow()

    fun getRole() = viewModelScope.launch {
        _roleUiState.value = Event.Success(getRoleUseCase().getOrDefault(""))
    }

    fun deleteNoticeById(
        role: String,
        noticeId: Long
    ) = viewModelScope.launch {
        deleteNoticeByIdUseCase(
            role = role,
            noticeId = noticeId
        ).onSuccess {
            _deleteUiState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _deleteUiState.value = Event.NotFound }
            )
        }
    }

    fun getNoticeDetail(
        role: String,
        noticeId: Long
    ) = viewModelScope.launch {
        getNoticeDetailUseCase(
            role = role,
            noticeId = noticeId
        ).onSuccess {
            _noticeUiState.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _noticeUiState.value = Event.NotFound }
            )
        }
    }
}
