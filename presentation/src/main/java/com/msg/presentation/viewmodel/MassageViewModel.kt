package com.msg.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msg.presentation.viewmodel.util.Event
import com.msg.domain.model.massage.MassageInfoResponseModel
import com.msg.domain.model.massage.MassageListResponseModel
import com.msg.domain.usecase.massage.CancelMassageUseCase
import com.msg.domain.usecase.massage.ChangeMassageLimitUseCase
import com.msg.domain.usecase.massage.GetMassageInfoUseCase
import com.msg.domain.usecase.massage.GetMassageRankUseCase
import com.msg.domain.usecase.massage.RequestMassageUseCase
import com.msg.presentation.exception.exceptionHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class MassageViewModel @Inject constructor(
    private val getMassageInfoUseCase: GetMassageInfoUseCase,
    private val getMassageRankUseCase: GetMassageRankUseCase,
    private val requestMassageUseCase: RequestMassageUseCase,
    private val cancelMassageUseCase: CancelMassageUseCase,
    private val changeMassageLimitUseCase: ChangeMassageLimitUseCase
): ViewModel() {
    private val _getMassageInfo = MutableStateFlow<Event<MassageInfoResponseModel>>(Event.Loading)
    val getMassageInfo = _getMassageInfo.asStateFlow()

    private val _getMassageRank = MutableStateFlow<Event<List<MassageListResponseModel>>>(Event.Loading)
    val getMassageRank = _getMassageRank.asStateFlow()

    private val _requestMassage = MutableStateFlow<Event<ResponseBody>>(Event.Loading)
    val requestMassage = _requestMassage.asStateFlow()

    private val _cancelMassage = MutableStateFlow<Event<ResponseBody>>(Event.Loading)
    val cancelMassage = _cancelMassage.asStateFlow()

    private val _changeMassageLimit = MutableStateFlow<Event<Nothing>>(Event.Loading)
    val changeMassageLimit = _changeMassageLimit.asStateFlow()

    fun getMassageInfo(role: String) = viewModelScope.launch {
        getMassageInfoUseCase(role).onSuccess {
            _getMassageInfo.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                unauthorizedAction = { _getMassageInfo.value = Event.Unauthorized }
            )
        }
    }

    fun getMassageRank(role: String) = viewModelScope.launch {
        getMassageRankUseCase(role).onSuccess {
            _getMassageRank.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                unauthorizedAction = { _getMassageRank.value = Event.Unauthorized }
            )
        }
    }

    fun requestMassage(role: String) = viewModelScope.launch {
        requestMassageUseCase(role).onSuccess {
            val response = it.string()

            if (response.isNotEmpty()) {
                val jsonObject = JSONObject(response)
                val code = jsonObject.getInt("code")

                if (code == 202) _requestMassage.value = Event.Accepted
            } else _requestMassage.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                conflictAction = { _requestMassage.value = Event.Conflict }
            )
        }
    }

    fun cancelMassage(role: String) = viewModelScope.launch {
        cancelMassageUseCase(role).onSuccess {
            val response = it.string()

            if (response.isNotEmpty()) {
                val jsonObject = JSONObject(response)
                val code = jsonObject.getInt("code")

                if (code == 202) _cancelMassage.value = Event.Accepted
            } else _cancelMassage.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                conflictAction = { _cancelMassage.value = Event.Conflict }
            )
        }
    }

    fun changeMassageLimit(
        role: String,
        limit: Int
    ) = viewModelScope.launch {
        changeMassageLimitUseCase(
            role = role,
            limit = limit
        ).onSuccess {
            _changeMassageLimit.value = Event.Success()
        }.onFailure {
            it.exceptionHandling(
                unauthorizedAction = { _changeMassageLimit.value = Event.Unauthorized },
                forbiddenAction = { _changeMassageLimit.value = Event.ForBidden }
            )
        }
    }
}