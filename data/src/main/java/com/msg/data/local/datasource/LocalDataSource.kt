package com.msg.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveToken(
        accessToken: String,
        refreshToken: String,
        expiresAt: String
    )

    suspend fun saveRole(role: String)

    fun getAccessToken(): Flow<String>

    fun getRefreshToken(): Flow<String>

    fun getExpiresAt(): Flow<String>

    fun getRole(): Flow<String>
}