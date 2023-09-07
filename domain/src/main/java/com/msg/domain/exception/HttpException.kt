package com.msg.domain.exception

class BadRequestException(
    override val message: String?
): RuntimeException()

class UnauthorizedException(
    override val message: String?
): RuntimeException()

class ForbiddenException(
    override val message: String?
): RuntimeException()

class NotFoundException(
    override val message: String?
): RuntimeException()

class TimeOutException(
    override val message: String?
) : RuntimeException()

class ConflictException(
    override val message: String?
): RuntimeException()

class ServerException(
    override val message: String?
): RuntimeException()

class OtherHttpException(
    val code: Int?,
    override val message: String?
): RuntimeException()

class UnknownException(
    override val message: String?
): RuntimeException()