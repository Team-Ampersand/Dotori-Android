package com.msg.data.remote.network

import com.msg.data.remote.dto.music.MusicRequest
import com.msg.data.remote.dto.music.MusicResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicApi {
    @GET("/{role}/music")
    suspend fun getMusics(
        @Path("role") role: String,
        @Query("date") date: String
    ): List<MusicResponse>

    @POST("/{role}/music")
    suspend fun requestMusic(
        @Path("role") role: String,
        @Body body: MusicRequest
    )

    @DELETE("/{role}/music/{music_id}")
    suspend fun deleteMusic(
        @Path("role") role: String,
        @Path("music_id") musicId: Long
    )
}