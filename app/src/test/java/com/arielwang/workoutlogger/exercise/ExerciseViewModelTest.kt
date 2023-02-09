package com.arielwang.workoutlogger.exercise

import app.cash.turbine.test
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.database.model.WorkoutData
import com.arielwang.workoutlogger.features.exercisecard.ui.ExerciseCardDestination
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.domain.ExerciseRepository
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen.Card
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen.ExerciseView
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen.ExerciseViewModel
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.TrackDestination
import com.arielwang.workoutlogger.navigate.FakeNavigationBehaviour
import com.arielwang.workoutlogger.navigate.FakeNavigatorRule
import com.arielwang.workoutlogger.testutils.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val fakeExerciseRepository = object : ExerciseRepository {
        override suspend fun getAllExerciseCards(): List<ExerciseCard> {
            TODO("Not yet implemented")
        }

        override fun getAllExerciseCardsFlow(): Flow<List<ExerciseCard>> = MutableStateFlow(
            listOf(
                ExerciseCard(name = "abs"),
                ExerciseCard(name = "Back"),
                ExerciseCard(name = "Biceps")
            )
        )
    }
    private val fakeExerciseSharedStateManager = object : ExerciseTrackSharedStateManager {
        private var state: WorkoutData? = null

        override fun updateState(state: WorkoutData) {
            this.state = state
        }

        override fun getState(): WorkoutData { return checkNotNull(state) }
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
                            Card("Biceps")
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
                WorkoutData(type = emptyList<String>()),
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
                            Card("Biceps")
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When topbar add button is clicked, go to ExerciseCard Screen`() {
        runTest {
            val viewModel = generateViewModel()
            val createNewExerciseCard = ExerciseView.Action.AddExerciseCard

            viewModel.onUiAction(createNewExerciseCard)

            assertEquals(
                FakeNavigationBehaviour.Navigate(ExerciseCardDestination.route()),
                fakeNavigator.awaitNextScreen()
            )

        }
    }

    private fun generateViewModel() = ExerciseViewModel(fakeNavigator, fakeExerciseRepository, fakeExerciseSharedStateManager)
}