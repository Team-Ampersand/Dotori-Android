package com.msg.presentation.viewmodel.notice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.notice.request.NoticeRequestModel
import com.msg.domain.model.notice.response.NoticeDetailResponseModel
import com.msg.domain.usecase.auth.GetRoleUseCase
import com.msg.domain.usecase.notice.GetNoticeDetailUseCase
import com.msg.domain.usecase.notice.ModifyNoticeUseCase
import com.msg.domain.usecase.notice.WriteNoticeUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class NoticeEditViewModel @Inject constructor(
    private val getRoleUseCase: GetRoleUseCase,
    private val writeNoticeUseCase: WriteNoticeUseCase,
    private val modifyNoticeUseCase: ModifyNoticeUseCase,
    private val getNoticeDetailUseCase: GetNoticeDetailUseCase
): ViewModel() {
    private val _roleUiState = MutableStateFlow<Event<String>>(Event.Loading)
    val roleUiState = _roleUiState.asStateFlow()

    private val _writeUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val writeUiState = _writeUiState.asStateFlow()

    private val _modifyUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val modifyUiState = _modifyUiState.asStateFlow()

    private val _noticeUiState = MutableStateFlow<Event<NoticeDetailResponseModel>>(Event.Loading)
    val noticeUiState = _noticeUiState.asStateFlow()

    fun getRole() = viewModelScope.launch {
        _roleUiState.value = Event.Success(getRoleUseCase().getOrDefault(""))
    }

    fun writeNotice(
        role: String,
        files: List<MultipartBody.Part>,
        noticeRequestModel: NoticeRequestModel
    ) = viewModelScope.launch {
        writeNoticeUseCase(
            role = role,
            files = files,
            noticeRequest = noticeRequestModel
        ).onSuccess {
            _writeUiState.value = Event.Success()
        }.onFailure {

        }
    }

    fun modifyNotice(
        role: String,
        noticeId: Long,
        body: NoticeRequestModel
    ) = viewModelScope.launch {
        modifyNoticeUseCase(
            role = role,
            noticeId = noticeId,
            body = body
        ).onSuccess {
            _modifyUiState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _modifyUiState.value = Event.NotFound }
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
