package com.msg.data.remote.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.msg.data.BuildConfig
import com.msg.data.local.datasource.LocalDataSource
import com.msg.domain.exception.TokenExpiredException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.time.LocalDateTime
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val localDataSource: LocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val path = request.url.encodedPath
        val ignorePath = listOf("/auth")
        val currentTime = LocalDateTime.now()

        ignorePath.forEach {
            if (path.contains(it)) {
                return chain.proceed(request)
            }
        }

        val refreshToken = runBlocking { localDataSource.getRefreshToken().first() }
        val accessTokenExp = runBlocking { LocalDateTime.parse(localDataSource.getExpiresAt().first()) }
        val refreshTokenExp = accessTokenExp.plusMonths(6)

        if (currentTime.isAfter(refreshTokenExp)) throw TokenExpiredException()

        if (currentTime.isAfter(accessTokenExp)) {
            val client = OkHttpClient()
            val reissueRequest = Request.Builder()
                .url("${BuildConfig.DEVELOP_URL}/auth")
                .patch("".toRequestBody("application/json".toMediaType()))
                .addHeader(
                    name = "refreshToken",
                    value = "Bearer $refreshToken"
                )
                .build()
            val response = client.newCall(reissueRequest).execute()

            if (response.isSuccessful) {
                val token = JsonParser.parseString(response.body!!.string()) as JsonObject
                runBlocking {
                    localDataSource.saveToken(
                        accessToken = token["access_token"].asString,
                        refreshToken = token["refresh_token"].asString,
                        expiresAt = token["expires_at"].asString
                    )
                    localDataSource.saveRole(token["role"].asString)
                }
            } else throw TokenExpiredException()
        }
        val accessToken = runBlocking { localDataSource.getAccessToken().first() }
        builder.addHeader("Authorization", "Bearer $accessToken")

        return chain.proceed(builder.build())
    }
}