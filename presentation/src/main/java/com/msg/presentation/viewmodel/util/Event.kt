package com.msg.presentation.viewmodel.util

sealed class Event<out T>(val data: T? = null) {
    object Loading : Event<Nothing>()
    class Success<T>(data: T? = null) : Event<T>(data = data)
    object BadRequest : Event<Nothing>()
    object Unauthorized : Event<Nothing>()
    object ForBidden : Event<Nothing>()
    object NotFound : Event<Nothing>()
    object NotAcceptable : Event<Nothing>()
    object TimeOut : Event<Nothing>()
    object Conflict : Event<Nothing>()
    object Server : Event<Nothing>()
    object UnKnown : Event<Nothing>()
}