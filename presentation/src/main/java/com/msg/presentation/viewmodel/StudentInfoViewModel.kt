package com.msg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.student_info.SearchStudentInfoResponseModel
import com.msg.domain.model.student_info.StudentInfoRequestModel
import com.msg.domain.model.student_info.StudentInfoResponseModel
import com.msg.domain.usecase.self_study.BanCancelSelfStudyUseCase
import com.msg.domain.usecase.self_study.BanSelfStudyUseCase
import com.msg.domain.usecase.student_info.GetAllStudentInfoUseCase
import com.msg.domain.usecase.student_info.GetSearchStudentInfoUseCase
import com.msg.domain.usecase.student_info.ModifyStudentInfoUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudentInfoViewModel @Inject constructor(
    private val getAllStudentInfoUseCase: GetAllStudentInfoUseCase,
    private val getSearchStudentInfoUseCase: GetSearchStudentInfoUseCase,
    private val modifyStudentInfoUseCase: ModifyStudentInfoUseCase,
    private val banSelfStudyUseCase: BanSelfStudyUseCase,
    private val banCancelSelfStudyUseCase: BanCancelSelfStudyUseCase
): ViewModel() {
    private val _getAllStudentInfoUiState = MutableStateFlow<Event<List<StudentInfoResponseModel>>>(Event.Loading)
    val getAllStudentInfoUiState = _getAllStudentInfoUiState.asStateFlow()

    private val _getSearchStudentInfoUiState = MutableStateFlow<Event<List<SearchStudentInfoResponseModel>>>(Event.Loading)
    val getSearchStudentInfoUiState = _getAllStudentInfoUiState.asStateFlow()

    private val _modifyStudentInfoUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val modifyStudentInfoUiState = _modifyStudentInfoUiState.asStateFlow()

    private val _banSelfStudyUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val banSelfStudyUiState = _banSelfStudyUiState.asStateFlow()

    private val _banCancelSelfStudyUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val banCancelSelfStudyUiState = _banCancelSelfStudyUiState.asStateFlow()

    fun getAllStudentInfo() = viewModelScope.launch {
        getAllStudentInfoUseCase()
            .onSuccess {
                _getAllStudentInfoUiState.value = Event.Success(it)
            }
            .onFailure {

            }
    }

    fun getSearchStudentInfo(
        name: String?,
        gender: String?,
        classNum: String?,
        grade: String?,
        role: String?,
        selfStudy: Boolean?
    ) = viewModelScope.launch {
        getSearchStudentInfoUseCase(
            name = name,
            gender = gender,
            classNum = classNum,
            grade = grade,
            role = role,
            selfStudy = selfStudy
        ).onSuccess {
            _getSearchStudentInfoUiState.value = Event.Success(it)
        }.onFailure {

        }
    }

    fun modifyStudentInfo(body: StudentInfoRequestModel) = viewModelScope.launch {
        modifyStudentInfoUseCase(body)
            .onSuccess {
                _modifyStudentInfoUiState.value = Event.Success()
            }
            .onFailure {
                it.exceptionHandling(
                    notFoundAction = { _modifyStudentInfoUiState.value = Event.NotFound }
                )
            }
    }

    fun banSelfStudy(
        role: String,
        userId: Long
    ) = viewModelScope.launch {
        banSelfStudyUseCase(
            role = role,
            userId = userId
        ).onSuccess {
            _banSelfStudyUiState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _banSelfStudyUiState.value = Event.NotFound }
            )
        }
    }

    fun banCancelSelfStudy(
        role: String,
        userId: Long
    ) = viewModelScope.launch {
        banCancelSelfStudyUseCase(
            role = role,
            userId = userId
        ).onSuccess {
            _banCancelSelfStudyUiState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _banCancelSelfStudyUiState.value = Event.NotFound }
            )
        }
    }
}