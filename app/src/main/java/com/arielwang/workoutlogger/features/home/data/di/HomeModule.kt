package com.arielwang.workoutlogger.features.home.data.di

import com.arielwang.workoutlogger.features.home.data.repository.HomeRepositoryImpl
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class HomeModule {
  @Binds
  abstract fun bindHomeRepository(
    impl: HomeRepositoryImpl,
  ): HomeRepository
}