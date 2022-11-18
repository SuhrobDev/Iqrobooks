package com.soul.iqrobooks.data.di.module

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soul.iqrobooks.data.remote.ApiService
import com.soul.iqrobooks.utils.Constants
import com.soul.iqrobooks.utils.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        sharedPref: SharedPref
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL)
            client(OkHttpClient.Builder().addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
//                    request.newBuilder().header("Content-Type", "application/json")
                    val newRequest = if (sharedPref.getToken().isNullOrEmpty())
                        request.newBuilder()
                    else request.newBuilder()
                        .header("Authorization", "Bearer ${sharedPref.getToken()}")
                    chain.proceed(newRequest.build())
                }
                .build())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideMainService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }
}