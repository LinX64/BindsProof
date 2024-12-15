package com.client.bindsproof.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProvidesQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BindsQualifier
