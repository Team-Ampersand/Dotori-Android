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
        private val ACCESS_EXP = stringPreferencesKey("access_exp")
        private val REFRESH_EXP = stringPreferencesKey("refresh_exp")
    }

    override suspend fun saveToken(accessToken: String, refreshToken: String, accessExp: String, refreshExp: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
            it[REFRESH_TOKEN] = refreshToken
            it[ACCESS_EXP] = accessExp
            it[REFRESH_EXP] = refreshExp
        }
    }

    override fun getAccessToken(): Flow<String> = context.dataStore.data.map { it[ACCESS_TOKEN] ?: "" }

    override fun getRefreshToken(): Flow<String> = context.dataStore.data.map { it[REFRESH_TOKEN] ?: "" }

    override fun getAccessTokenExp(): Flow<String> = context.dataStore.data.map { it[ACCESS_EXP] ?: "" }

    override fun getRefreshTokenExp(): Flow<String> = context.dataStore.data.map { it[REFRESH_EXP] ?: "" }
}