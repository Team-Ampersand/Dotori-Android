package com.msg.data.remote.util

import com.msg.domain.exception.BadRequestException
import com.msg.domain.exception.ConflictException
import com.msg.domain.exception.ForbiddenException
import com.msg.domain.exception.NoInternetException
import com.msg.domain.exception.NotFoundException
import com.msg.domain.exception.OtherHttpException
import com.msg.domain.exception.ServerException
import com.msg.domain.exception.TimeOutException
import com.msg.domain.exception.TokenExpiredException
import com.msg.domain.exception.UnauthorizedException
import com.msg.domain.exception.UnknownException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(call: suspend () -> T): T {
    return try {
        call.invoke()
    } catch (e: HttpException) {
        val message = e.message
        throw when (e.code()) {
            400 -> BadRequestException(message = message)
            401 -> UnauthorizedException(message = message)
            403 -> ForbiddenException(message = message)
            404 -> NotFoundException(message = message)
            409 -> ConflictException(message = message)
            500, 501, 502, 503 -> ServerException(message = message)
            else -> OtherHttpException(
                code = e.code(),
                message = message
            )
        }
    } catch (e: SocketTimeoutException) {
        throw TimeOutException(message = e.message)
    } catch (e: UnknownHostException) {
        throw NoInternetException()
    } catch (e: TokenExpiredException) {
        throw TokenExpiredException()
    } catch (e: Exception) {
        throw UnknownException(message = e.message)
    }
}