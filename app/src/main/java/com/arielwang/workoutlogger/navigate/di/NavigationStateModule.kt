package com.arielwang.workoutlogger.navigate.di

import com.arielwang.workoutlogger.navigate.utils.NavigationStateManager
import com.arielwang.workoutlogger.navigate.utils.NavigationStateManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigationStateModule {
  @Binds
  abstract fun bindNavigationStateManager(impl: NavigationStateManagerImpl): NavigationStateManager
}
