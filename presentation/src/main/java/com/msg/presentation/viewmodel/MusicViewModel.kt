package com.msg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.music.request.MusicRequestModel
import com.msg.domain.model.music.response.MusicModel
import com.msg.domain.model.music.response.YoutubeResponseModel
import com.msg.domain.usecase.auth.GetRoleUseCase
import com.msg.domain.usecase.music.DeleteMusicUseCase
import com.msg.domain.usecase.music.GetMusicsUseCase
import com.msg.domain.usecase.music.GetYoutubeMusicUseCase
import com.msg.domain.usecase.music.RequestMusicUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import com.msg.presentation.viewmodel.util.getRolePath
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MusicViewModel @Inject constructor(
    private val getMusicsUseCase: GetMusicsUseCase,
    private val requestMusicUseCase: RequestMusicUseCase,
    private val deleteMusicUseCase: DeleteMusicUseCase,
    private val getYoutubeMusicUseCase: GetYoutubeMusicUseCase,
    private val getRoleUseCase: GetRoleUseCase
) : ViewModel() {
    private val _roleUiState = MutableStateFlow<Event<String>>(Event.Loading)
    val roleUiState = _roleUiState.asStateFlow()

    private val _musicUiState = MutableStateFlow<Event<List<MusicModel>>>(Event.Loading)
    val musicUiState = _musicUiState.asStateFlow()

    private val _requestUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val requestUiState = _requestUiState.asStateFlow()

    private val _deleteUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val deleteUiState = _deleteUiState.asStateFlow()

    fun getRole() = viewModelScope.launch {
        _roleUiState.value = Event.Success(getRoleUseCase.getRolePath())
    }

    fun getMusics(role: String, date: String) = viewModelScope.launch {
        getMusicsUseCase(role, date)
            .onSuccess { result ->
                _musicUiState.value = Event.Success(
                    result.map {
                        val youtubeMusic = getYoutubeMusicUseCase(it.url).getOrDefault(
                            YoutubeResponseModel(title = "", thumbnailUrl = "")
                        )
                        MusicModel(
                            id = it.id,
                            url = it.url,
                            username = it.username,
                            email = it.email,
                            createdTime = it.createdTime,
                            stuNum = it.stuNum,
                            title = youtubeMusic.title,
                            thumbnailUrl = youtubeMusic.thumbnailUrl
                        )
                    }
                )
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
