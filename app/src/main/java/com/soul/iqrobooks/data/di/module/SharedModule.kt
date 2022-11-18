package com.soul.iqrobooks.data.di.module

import android.app.Application
import com.soul.iqrobooks.utils.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedModule {

    @Provides
    fun provideShared(application: Application) = SharedPref(application.applicationContext)
}