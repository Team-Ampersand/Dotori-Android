package com.msg.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
): LocalDataSource {
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        private val EXPIRES_AT = stringPreferencesKey("expires_at")
        private val ROLE = stringPreferencesKey("roles")
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String, expiresAt: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[EXPIRES_AT] = expiresAt
        }
    }

    override suspend fun saveRole(roles: String) {
        context.dataStore.edit {
            it[ROLE] = roles
        }
    }

    override fun getAccessToken(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN] ?: "" }

    override fun getRefreshToken(): Flow<String> = context.dataStore.data.map { it[REFRESH_TOKEN] ?: "" }

    override fun getExpiresAt(): Flow<String> = context.dataStore.data.map { it[EXPIRES_AT] ?: "" }

    override fun getRole(): Flow<String> = context.dataStore.data.map { it[ROLE] ?: "" }
}