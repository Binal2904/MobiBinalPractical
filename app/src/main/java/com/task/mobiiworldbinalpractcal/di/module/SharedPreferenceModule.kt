package com.task.mobiiworldbinalpractcal.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

    @Provides
    @Singleton
    fun provideSharedPreferenceName(): String = "SharedPref"

    @Provides
    @Singleton
    fun provideSharedPreferenceMode(): Int = Context.MODE_PRIVATE

    @Provides
    @Singleton
    fun provideSharePreference(
        @ApplicationContext context: Context,
        sharedPreferenceName: String,
        mode: Int
    ): SharedPreferences {
        return context.getSharedPreferences(sharedPreferenceName, mode)
    }
}