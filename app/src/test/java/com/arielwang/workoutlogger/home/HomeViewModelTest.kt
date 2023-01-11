package com.arielwang.workoutlogger.home

import app.cash.turbine.test
import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import com.arielwang.workoutlogger.features.home.ui.screen.HomeView
import com.arielwang.workoutlogger.features.home.ui.screen.HomeViewModel
import com.arielwang.workoutlogger.navigate.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    private val fakeNavigator = FakeNavigator()
    private val fakeHomeRepository = object : HomeRepository{
        override suspend fun getAllExercises(): List<ExerciseFlow> = listOf(ExerciseFlow(type = listOf(
            "Test")))
    }

    @Test
    fun `init state is expected`() {
        runTest {
            val homeViewModel = HomeViewModel(fakeNavigator, fakeHomeRepository)
            homeViewModel.uiState.test {
                assertEquals(awaitItem() == HomeView.State(exercises = listOf("Test")))
            }
        }
    }




}