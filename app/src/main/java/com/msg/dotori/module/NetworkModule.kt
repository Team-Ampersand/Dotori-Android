package com.msg.dotori.module

import android.util.Log
import com.msg.data.remote.network.AuthApi
import com.msg.data.remote.network.MassageApi
import com.msg.data.remote.network.MusicApi
import com.msg.data.remote.network.NoticeApi
import com.msg.data.remote.network.RuleViolationApi
import com.msg.data.remote.network.SelfStudyApi
import com.msg.data.remote.network.StudentInfoApi
import com.msg.data.remote.util.AuthInterceptor
import com.msg.dotori.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message -> Log.v("HTTP", message) }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.DEVELOP_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideMusicApi(retrofit: Retrofit): MusicApi = retrofit.create(MusicApi::class.java)

    @Provides
    @Singleton
    fun provideStudentInfoApi(retrofit: Retrofit): StudentInfoApi = retrofit.create(StudentInfoApi::class.java)

    @Provides
    @Singleton
    fun provideSelfStudyApi(retrofit: Retrofit): SelfStudyApi = retrofit.create(SelfStudyApi::class.java)

    @Provides
    @Singleton
    fun provideMassageApi(retrofit: Retrofit): MassageApi = retrofit.create(MassageApi::class.java)

    @Provides
    @Singleton
    fun provideNoticeApi(retrofit: Retrofit): NoticeApi = retrofit.create(NoticeApi::class.java)

    @Provides
    @Singleton
    fun provideRuleViolationApi(retrofit: Retrofit): RuleViolationApi = retrofit.create(RuleViolationApi::class.java)
}
