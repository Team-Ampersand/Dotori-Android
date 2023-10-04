package com.msg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.domain.model.rule_violation.request.RuleViolationRequestModel
import com.msg.domain.model.rule_violation.response.RuleViolationResponseModel
import com.msg.domain.model.rule_violation.response.SearchRuleViolationResponseModel
import com.msg.domain.model.student_info.StudentInfoResponseModel
import com.msg.domain.usecase.rule_violation.DeleteRuleViolationUseCase
import com.msg.domain.usecase.rule_violation.GetDetailRuleViolationUseCase
import com.msg.domain.usecase.rule_violation.GetSelfRuleViolationUseCase
import com.msg.domain.usecase.rule_violation.PostRuleViolationUseCase
import com.msg.domain.usecase.rule_violation.SearchRuleViolationUseCase
import com.msg.domain.usecase.student_info.GetAllStudentInfoUseCase
import com.msg.presentation.exception.exceptionHandling
import com.msg.presentation.viewmodel.util.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RuleViolationViewModel @Inject constructor(
    private val getDetailRuleViolationUseCase: GetDetailRuleViolationUseCase,
    private val getSelfRuleViolationUseCase: GetSelfRuleViolationUseCase,
    private val getAllStudentInfoUseCase: GetAllStudentInfoUseCase,
    private val searchRuleViolationUseCase: SearchRuleViolationUseCase,
    private val postRuleViolationUseCase: PostRuleViolationUseCase,
    private val deleteRuleViolationUseCase: DeleteRuleViolationUseCase
): ViewModel() {
    private val _detailRuleViolationUiState = MutableStateFlow<Event<List<RuleViolationResponseModel>>>(Event.Loading)
    val detailRuleViolationUiState = _detailRuleViolationUiState

    private val _selfRuleViolationUiState = MutableStateFlow<Event<List<RuleViolationResponseModel>>>(Event.Loading)
    val selfRuleViolationUiState = _selfRuleViolationUiState

    private val _studentInfoUiState = MutableStateFlow<Event<List<StudentInfoResponseModel>>>(Event.Loading)
    val studentInfoUiState = _studentInfoUiState

    private val _searchRuleViolationUiState = MutableStateFlow<Event<List<SearchRuleViolationResponseModel>>>(Event.Loading)
    val searchRuleViolationUiState = _searchRuleViolationUiState

    private val _postRuleViolationUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val postRuleViolationUiState = _postRuleViolationUiState

    private val _deleteRuleViolationUiState = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val deleteRuleViolationUiState = _deleteRuleViolationUiState.asStateFlow()

    fun getDetailRuleViolation(
        role: String,
        stuNum: String
    ) = viewModelScope.launch {
        getDetailRuleViolationUseCase(
            role = role,
            stuNum = stuNum
        ).onSuccess {
            _detailRuleViolationUiState.value = Event.Success(it)
        }.onFailure {}
    }

    fun getSelfRuleViolation(role: String) = viewModelScope.launch {
        getSelfRuleViolationUseCase(role)
            .onSuccess {
                _selfRuleViolationUiState.value = Event.Success(it)
            }
            .onFailure {}
    }

    fun getAllStudentInfo() = viewModelScope.launch {
        getAllStudentInfoUseCase()
            .onSuccess {
                _studentInfoUiState.value = Event.Success()
            }
            .onFailure {}
    }

    fun searchRuleViolation(
        role: String,
        memberName: String,
        stuNum: String,
        gender: String
    ) = viewModelScope.launch {
        searchRuleViolationUseCase(
            role = role,
            memberName = memberName,
            stuNum = stuNum,
            gender = gender
        ).onSuccess {
            _searchRuleViolationUiState.value = Event.Success(it)
        }.onFailure {}
    }

    fun postRuleViolation(
        role: String,
        ruleViolationRequest: RuleViolationRequestModel
    ) = viewModelScope.launch {
        postRuleViolationUseCase(
            role = role,
            ruleViolationRequest = ruleViolationRequest
        ).onSuccess {
            _postRuleViolationUiState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                 notFoundAction = { _postRuleViolationUiState.value = Event.NotFound }
            )
        }
    }

    fun deleteRuleViolation(
        role: String,
        ruleId: String
    ) = viewModelScope.launch {
        deleteRuleViolationUseCase(
            role = role,
            ruleId = ruleId
        ).onSuccess {
            _deleteRuleViolationUiState.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                notFoundAction = { _deleteRuleViolationUiState.value = Event.NotFound }
            )
        }
    }
}