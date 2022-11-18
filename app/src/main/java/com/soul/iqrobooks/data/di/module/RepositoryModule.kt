package com.soul.iqrobooks.data.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Provides
//    fun provideStudentRepository(mainService: ApiService): StudentRepo {
//        return StudentRepoImpl(mainService)
//    }
//
//    @Provides
//    fun provideUserRepository(mainService: ApiService): UserRepo {
//        return UserRepoImpl(mainService)
//    }
//
}