package com.msg.presentation.exception

import com.msg.domain.exception.BadRequestException
import com.msg.domain.exception.ConflictException
import com.msg.domain.exception.ForbiddenException
import com.msg.domain.exception.NotFoundException
import com.msg.domain.exception.ServerException
import com.msg.domain.exception.TimeOutException
import com.msg.domain.exception.TokenExpiredException
import com.msg.domain.exception.UnauthorizedException

fun Throwable.exceptionHandling(
    badRequestAction: () -> Unit = {},
    unauthorizedAction: () -> Unit = {},
    forbiddenAction: () -> Unit = {},
    notFoundAction: () -> Unit = {},
    timeOutAction: () -> Unit = {},
    conflictAction: () -> Unit = {},
    serverAction: () -> Unit = {},
    unknownAction: () -> Unit = {},
) {
    when (this) {
        is BadRequestException -> badRequestAction()
        is UnauthorizedException, is TokenExpiredException -> unauthorizedAction()
        is ForbiddenException -> forbiddenAction()
        is NotFoundException -> notFoundAction()
        is TimeOutException -> timeOutAction()
        is ConflictException -> conflictAction()
        is ServerException -> serverAction()
        else -> unknownAction()
    }
}