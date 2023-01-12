package com.arielwang.workoutlogger.home

import app.cash.turbine.test
import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import com.arielwang.workoutlogger.features.home.ui.screen.HomeView
import com.arielwang.workoutlogger.features.home.ui.screen.HomeViewModel
import com.arielwang.workoutlogger.navigate.FakeNavigationBehaviour
import com.arielwang.workoutlogger.navigate.FakeNavigator
import com.arielwang.workoutlogger.navigate.FakeNavigatorRule
import com.arielwang.workoutlogger.testutils.CoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val navigatorRule = FakeNavigatorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val fakeNavigator = navigatorRule.navigator
    private val fakeHomeRepository = object : HomeRepository{
        override suspend fun getAllExercises(): List<ExerciseFlow>
        = listOf(
            ExerciseFlow(
                type = listOf("Chest","Abs"),
                weight = 56.8,
                reps = 2,
                hours = 1,
                mins = 23,
                secs = 30,
                comment = "Chest exercise for 45min, abs exercise for 45min. Need to add stretching"
            )
        )
    }

    @Test
    fun `init state is expected`() {
        runTest {
            generateViewModel().uiState.test {
                assertEquals(
                    HomeView.State(
                        exercises = listOf(
                            "[Chest, Abs] \n" + "56.8, 2\n 1h 23min 30secs \n Chest exercise for 45min, abs exercise for 45min. Need to add stretching"
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When Next button is clicked, go to next screen`() {
        runTest {
            val goToNextPageButton = HomeView.Action.GoToNextPage
            generateViewModel().onUiAction(goToNextPageButton)

            assertEquals(
                fakeNavigator.awaitNextScreen(),
                FakeNavigationBehaviour.Navigate("Exercise")
            )
        }
    }

    private fun generateViewModel() = HomeViewModel(fakeNavigator, fakeHomeRepository)
}