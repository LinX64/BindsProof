package com.client.bindsproof.di

import com.client.bindsproof.NewsRepository
import com.client.bindsproof.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface BindsModuleWithQualifier {

    @BindsQualifier
    @Binds
    fun bindRepository(impl: NewsRepositoryImpl): NewsRepository
}