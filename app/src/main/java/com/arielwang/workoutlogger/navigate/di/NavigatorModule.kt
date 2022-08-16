package com.arielwang.workoutlogger.navigate.di

import com.arielwang.workoutlogger.navigate.Navigator
import com.arielwang.workoutlogger.navigate.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigatorModule {

  @Binds
  @Singleton
  abstract fun bindRootNavigator(impl: NavigatorImpl): Navigator

}