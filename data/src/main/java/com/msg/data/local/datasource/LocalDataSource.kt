package com.msg.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveToken(
        accessToken: String,
        refreshToken: String,
        accessExp: String,
        refreshExp: String
    )

    fun getAccessToken(): Flow<String>

    fun getRefreshToken(): Flow<String>

    fun getAccessTokenExp(): Flow<String>

    fun getRefreshTokenExp(): Flow<String>
}