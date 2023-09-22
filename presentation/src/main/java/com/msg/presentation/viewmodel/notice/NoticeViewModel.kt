package com.msg.presentation.viewmodel.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.notice.response.NoticeResponseModel
import com.msg.domain.usecase.auth.GetRoleUseCase
import com.msg.domain.usecase.notice.DeleteNoticeByIdListUseCase
import com.msg.domain.usecase.notice.GetAllNoticeUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getRoleUseCase: GetRoleUseCase,
    private val getAllNoticeUseCase: GetAllNoticeUseCase,
    private val deleteNoticeByIdListUseCase: DeleteNoticeByIdListUseCase,
) : ViewModel() {
    private val _roleUiState = MutableStateFlow<Event<String>>(Event.Loading)
    val roleUiState = _roleUiState.asStateFlow()

    private val _noticeUiState = MutableStateFlow<Event<List<NoticeResponseModel>>>(Event.Loading)
    val noticeUiState = _noticeUiState.asStateFlow()

    private val _deleteUiState = MutableStateFlow<Event<List<Nothing>>>(Event.Loading)
    val deleteUiState = _deleteUiState.asStateFlow()

    fun getRole() = viewModelScope.launch {
        _roleUiState.value = Event.Success(getRoleUseCase().getOrDefault(""))
    }

    fun getAllNotice(role: String) = viewModelScope.launch {
        getAllNoticeUseCase(role)
            .onSuccess {
                _noticeUiState.value = Event.Success(it)
            }.onFailure {

            }
    }

    fun deleteNoticeByIdList(
        role: String,
        body: List<Long>
    ) = viewModelScope.launch {
        deleteNoticeByIdListUseCase(
            role = role,
            body = body
        ).onSuccess {
            _deleteUiState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _deleteUiState.value = Event.NotFound }
            )
        }
    }
}
