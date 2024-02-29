package com.msg.data.repository

import com.msg.data.local.datasource.LocalDataSource
import com.msg.data.remote.datasource.auth.AuthDataSource
import com.msg.data.remote.dto.auth.asChangePasswordRequest
import com.msg.data.remote.dto.auth.asLoginRequest
import com.msg.data.remote.dto.auth.asLoginResponseModel
import com.msg.data.remote.dto.auth.asSignUpRequest
import com.msg.domain.model.auth.ChangePasswordRequestModel
import com.msg.domain.model.auth.LoginRequestModel
import com.msg.domain.model.auth.LoginResponseModel
import com.msg.domain.model.auth.SignUpRequestModel
import com.msg.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val localDataSource: LocalDataSource
): AuthRepository {
    override suspend fun login(loginRequest: LoginRequestModel): LoginResponseModel = authDataSource.login(loginRequest.asLoginRequest()).asLoginResponseModel()

    override suspend fun tokenReissue(): LoginResponseModel = authDataSource.tokenReissue().asLoginResponseModel()

    override suspend fun saveToken(
        accessToken: String,
        refreshToken: String,
        expiresAt: String
    ) = localDataSource.saveToken(
        accessToken,
        refreshToken,
        expiresAt
    )

    override suspend fun saveRole(roles: String) = localDataSource.saveRole(roles)

    override suspend fun getRole(): String = localDataSource.getRole().first()

    override suspend fun signUp(body: SignUpRequestModel) = authDataSource.signUp(body.asSignUpRequest())

    override suspend fun changePassword(body: ChangePasswordRequestModel) = authDataSource.changePassword(body.asChangePasswordRequest())
}
