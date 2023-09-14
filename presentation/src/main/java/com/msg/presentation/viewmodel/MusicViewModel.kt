package com.msg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.music.request.MusicRequestModel
import com.msg.domain.model.music.response.MusicResponseModel
import com.msg.domain.usecase.music.DeleteMusicUseCase
import com.msg.domain.usecase.music.GetMusicsUseCase
import com.msg.domain.usecase.music.RequestMusicUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getMusicsUseCase: GetMusicsUseCase,
    private val requestMusicUseCase: RequestMusicUseCase,
    private val deleteMusicUseCase: DeleteMusicUseCase
) : ViewModel() {
    private val _musicUiState = MutableStateFlow<Event<List<MusicResponseModel>>>(Event.Loading)
    val musicUiState = _musicUiState.asStateFlow()

    private val _requestUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val requestUiState = _requestUiState.asStateFlow()

    private val _deleteUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val deleteUiState = _deleteUiState.asStateFlow()

    fun getMusics(role: String, date: String) = viewModelScope.launch {
        getMusicsUseCase(role, date)
            .onSuccess {
                _musicUiState.value = Event.Success(it)
            }.onFailure {

            }
    }

    fun requestMusic(role: String, body: MusicRequestModel) = viewModelScope.launch {
        requestMusicUseCase(role, body)
            .onSuccess {
                _requestUiState.value = Event.Success()
            }.onFailure {
                it.exceptionHandling(
                    unauthorizedAction = { _requestUiState.value = Event.Unauthorized },
                    conflictAction = { _requestUiState.value = Event.Conflict }
                )
            }
    }

    fun deleteMusic(role: String, musicId: Long) = viewModelScope.launch {
        deleteMusicUseCase(role, musicId)
            .onSuccess {
                _deleteUiState.value = Event.Success()
            }.onFailure {
                it.exceptionHandling(
                    notFoundAction = { _requestUiState.value = Event.NotFound }
                )
            }
    }
}