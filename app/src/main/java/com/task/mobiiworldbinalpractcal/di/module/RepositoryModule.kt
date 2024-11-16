package com.task.mobiiworldbinalpractcal.di.module

import com.task.mobiiworldbinalpractcal.di.qualifier.BaseApiService
import com.task.mobiiworldbinalpractcal.repository.MainRepository
import com.task.mobiiworldbinalpractcal.restfullapi.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryModule(
        @BaseApiService apiService: ApiService,
    ): MainRepository =
        MainRepository(apiService)
}