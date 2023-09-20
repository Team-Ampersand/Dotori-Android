package com.msg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.self_study.SelfStudyInfoResponseModel
import com.msg.domain.model.self_study.SelfStudyListResponseModel
import com.msg.domain.usecase.auth.GetRoleUseCase
import com.msg.domain.usecase.self_study.BanCancelSelfStudyUseCase
import com.msg.domain.usecase.self_study.BanSelfStudyUseCase
import com.msg.domain.usecase.self_study.CancelSelfStudyUseCase
import com.msg.domain.usecase.self_study.ChangeSelfStudyLimitUseCase
import com.msg.domain.usecase.self_study.CheckSelfStudyUseCase
import com.msg.domain.usecase.self_study.GetSelfStudyInfoUseCase
import com.msg.domain.usecase.self_study.GetSelfStudyListUseCase
import com.msg.domain.usecase.self_study.SearchSelfStudyStudentUseCase
import com.msg.domain.usecase.self_study.SelfStudyUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import com.msg.presentation.viewmodel.util.getRolePath
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SelfStudyViewModel @Inject constructor(
    private val getRoleUseCase: GetRoleUseCase,
    private val selfStudyInfoUseCase: GetSelfStudyInfoUseCase,
    private val selfStudyListUseCase: GetSelfStudyListUseCase,
    private val searchSelfStudyStudentUseCase: SearchSelfStudyStudentUseCase,
    private val banSelfStudyUseCase: BanSelfStudyUseCase,
    private val banCancelSelfStudyUseCase: BanCancelSelfStudyUseCase,
    private val selfStudyUseCase: SelfStudyUseCase,
    private val cancelSelfStudyUseCase: CancelSelfStudyUseCase,
    private val changeSelfStudyLimitUseCase: ChangeSelfStudyLimitUseCase,
    private val checkSelfStudyUseCase: CheckSelfStudyUseCase
): ViewModel() {
    private val _rolePathState = MutableStateFlow<Event<String>>(Event.Loading)
    val rolePathState = _rolePathState.asStateFlow()

    private val _selfStudyInfoState = MutableStateFlow<Event<SelfStudyInfoResponseModel>>(Event.Loading)
    val selfStudyInfoState = _selfStudyInfoState.asStateFlow()

    private val _selfStudyListState = MutableStateFlow<Event<List<SelfStudyListResponseModel>>>(Event.Loading)
    val selfStudyListState = _selfStudyListState.asStateFlow()

    private val _searchSelfStudyStudentState = MutableStateFlow<Event<List<SelfStudyListResponseModel>>>(Event.Loading)
    val searchSelfStudyStudentState = _searchSelfStudyStudentState.asStateFlow()

    private val _banSelfStudyState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val banSelfStudyState = _banSelfStudyState.asStateFlow()

    private val _banCancelSelfStudyState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val banCancelSelfStudyState = _banCancelSelfStudyState.asStateFlow()

    private val _selfStudyState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val selfStudyState = _selfStudyState.asStateFlow()

    private val _cancelSelfStudyState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val cancelSelfStudyState = _cancelSelfStudyState.asStateFlow()

    private val _changeSelfStudyLimitState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val changeSelfStudyLimitState = _changeSelfStudyLimitState.asStateFlow()

    private val _checkSelfStudyState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val checkSelfStudyState = _checkSelfStudyState.asStateFlow()

    fun getRole() = viewModelScope.launch {
        _rolePathState.value = Event.Success(getRoleUseCase.getRolePath())
    }

    fun getSelfStudyInfo(role: String) = viewModelScope.launch {
        selfStudyInfoUseCase(role)
            .onSuccess {
                _selfStudyInfoState.value = Event.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = { _selfStudyInfoState.value = Event.BadRequest }
                )
            }
    }

    fun getSelfStudyList(role: String) = viewModelScope.launch {
        selfStudyListUseCase(role)
            .onSuccess {
                _selfStudyListState.value = Event.Success(it)
            }.onFailure {
                it.exceptionHandling(
                    badRequestAction = { _selfStudyListState.value = Event.BadRequest }
                )
            }
    }

    fun searchSelfStudyStudent(
        role: String,
        name: String?,
        gender: String?,
        classNum: String?,
        grade: Int?
    ) = viewModelScope.launch {
        searchSelfStudyStudentUseCase(
            role = role,
            name = name,
            gender = gender,
            classNum = classNum,
            grade = grade
        ).onSuccess {
            _searchSelfStudyStudentState.value = Event.Success(it)
        }.onFailure {

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
            _banSelfStudyState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _banSelfStudyState.value = Event.NotFound }
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
            _banCancelSelfStudyState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _banCancelSelfStudyState.value = Event.NotFound }
            )
        }
    }

    fun selfStudy(role: String) = viewModelScope.launch {
        selfStudyUseCase(role)
            .onSuccess {
                val response = it.string()

                if (response.isNotEmpty()) {
                    val jsonObject = JSONObject(response)
                    val code = jsonObject.getInt("code")

                    if (code == 202) _selfStudyState.value = Event.Accepted
                } else _selfStudyState.value = Event.Success()
            }.onFailure {
                it.exceptionHandling(
                    conflictAction = { _selfStudyState.value = Event.Conflict }
                )
            }
    }

    fun cancelSelfStudy(role: String) = viewModelScope.launch {
        cancelSelfStudyUseCase(role)
            .onSuccess {
                val response = it.string()

                if (response.isNotEmpty()) {
                    val jsonObject = JSONObject(response)
                    val code = jsonObject.getInt("code")

                    if (code == 202) _cancelSelfStudyState.value = Event.Accepted
                } else _cancelSelfStudyState.value = Event.Success()
            }.onFailure {
                it.exceptionHandling(
                    conflictAction = { _cancelSelfStudyState.value = Event.Conflict }
                )
            }
    }

    fun changeSelfStudyLimit(
        role: String,
        limit: Int
    ) = viewModelScope.launch {
        changeSelfStudyLimitUseCase(
            role = role,
            limit = limit
        ).onSuccess {
            _changeSelfStudyLimitState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                unauthorizedAction = { _changeSelfStudyLimitState.value = Event.Unauthorized },
                forbiddenAction = { _changeSelfStudyLimitState.value = Event.ForBidden }
            )
        }
    }

    fun checkSelfStudy(
        role: String,
        memberId: Long,
        selfStudyCheck: Boolean
    ) = viewModelScope.launch {
        checkSelfStudyUseCase(
            role = role,
            memberId = memberId,
            selfStudyCheck = selfStudyCheck
        ).onSuccess {
            _checkSelfStudyState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                unauthorizedAction = { _checkSelfStudyState.value = Event.Unauthorized },
                forbiddenAction = { _checkSelfStudyState.value = Event.ForBidden }
            )
        }
    }
}