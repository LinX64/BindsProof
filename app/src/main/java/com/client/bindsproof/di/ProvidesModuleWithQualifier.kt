package com.client.bindsproof.di

import com.client.bindsproof.NewsRepository
import com.client.bindsproof.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ProvidesModuleWithQualifier {
    @ProvidesQualifier
    @Provides
    fun provideRepository(): NewsRepository = NewsRepositoryImpl()
}
