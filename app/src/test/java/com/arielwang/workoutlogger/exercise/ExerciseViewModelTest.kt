package com.arielwang.workoutlogger.exercise

import app.cash.turbine.test
import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.addexerciseflow.exercise.ui.screen.Card
import com.arielwang.workoutlogger.features.addexerciseflow.exercise.ui.screen.ExerciseView
import com.arielwang.workoutlogger.features.addexerciseflow.exercise.ui.screen.ExerciseViewModel
import com.arielwang.workoutlogger.features.addexerciseflow.shared.domain.ExerciseSharedStateManager
import com.arielwang.workoutlogger.features.addexerciseflow.track.ui.screen.TrackDestination
import com.arielwang.workoutlogger.features.home.ui.screen.HomeDestination
import com.arielwang.workoutlogger.navigate.FakeNavigationBehaviour
import com.arielwang.workoutlogger.navigate.FakeNavigatorRule
import com.arielwang.workoutlogger.testutils.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ExerciseViewModelTest {
    @get:Rule
    val navigatorRule = FakeNavigatorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val fakeNavigator = navigatorRule.navigator
    private val fakeExerciseSharedStateManager = object : ExerciseSharedStateManager {
        private var state: ExerciseFlow? = null

        override fun updateState(state: ExerciseFlow) {
            this.state = state
        }

        override fun getState(): ExerciseFlow { return checkNotNull(state) }

        override fun deleteState() { state = null }
    }

    @Test
    fun `init is expected`() {
        runTest {
            val viewModel = generateViewModel()

            viewModel.uiState.test {
                assertEquals(
                    ExerciseView.State(
                        cardList = listOf(
                            Card("abs"),
                            Card("Back"),
                            Card("Biceps"),
                            Card("Cardio"),
                            Card("Chest"),
                            Card("sdf"),
                            Card("werfwec"),
                            Card("sdfs"),
                            Card("efw"),
                            Card("ef"),
                            Card("srfgs"),
                            Card("hgf"),
                            Card("ert"),
                            Card("g"),
                            Card("ert")
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When Next button is clicked, update ExerciseFlow state to exerciseSharedStateManager and go to next screen `() {
        runTest {
            val viewModel = generateViewModel()
            val goToNextPageButton = ExerciseView.Action.GoToNextPage
            viewModel.onUiAction(goToNextPageButton)

            assertEquals(
                FakeNavigationBehaviour.Navigate(TrackDestination.route()),
                fakeNavigator.awaitNextScreen()
            )

            assertEquals(
                ExerciseFlow(type = emptyList<String>()),
                fakeExerciseSharedStateManager.getState()
            )
        }
    }

    @Test
    fun `When topbar back button is clicked, go back to previous page`() {
        runTest {
            val viewModel = generateViewModel()
            val goBackToPreviousPageButton = ExerciseView.Action.GoBackToPreviousPage
            viewModel.onUiAction(goBackToPreviousPageButton)

            assertEquals(
                FakeNavigationBehaviour.NavigateUp,
                fakeNavigator.awaitNextScreen()
            )
        }
    }

    @Test
    fun `When card is clicked, update card isSelected property of cardList`() {
        runTest {
            val viewModel = generateViewModel()
            val onCardClickedCardText = "abs"

            val onCardClickedAction = ExerciseView.Action.OnCardClicked(onCardClickedCardText)
            viewModel.onUiAction(onCardClickedAction)

            viewModel.uiState.test {
                assertEquals(
                    ExerciseView.State(
                        cardList = listOf(
                            Card("abs", isSelected = true),
                            Card("Back"),
                            Card("Biceps"),
                            Card("Cardio"),
                            Card("Chest"),
                            Card("sdf"),
                            Card("werfwec"),
                            Card("sdfs"),
                            Card("efw"),
                            Card("ef"),
                            Card("srfgs"),
                            Card("hgf"),
                            Card("ert"),
                            Card("g"),
                            Card("ert")
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }


    private fun generateViewModel() = ExerciseViewModel(fakeNavigator, fakeExerciseSharedStateManager)
}