package com.arielwang.workoutlogger.track

import app.cash.turbine.test
import com.arielwang.workoutlogger.database.model.WorkoutAddingFlow
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import com.arielwang.workoutlogger.features.workoutaddingflow.track.domain.repository.TrackRepository
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.TrackView
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.TrackViewModel
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
class TrackViewModelTest {
    @get:Rule
    val navigatorRule = FakeNavigatorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val fakeNavigator = navigatorRule.navigator

    private val fakeTrackRepository = object : TrackRepository {
        private var exercise: WorkoutAddingFlow? = null

        override suspend fun insertExercise(exercise: WorkoutAddingFlow) {
            this.exercise = exercise
        }

        fun getExercise(): WorkoutAddingFlow { return checkNotNull(exercise) }
    }

    private val fakeExerciseSharedStateManager = object : ExerciseTrackSharedStateManager {
        private var state: WorkoutAddingFlow? = WorkoutAddingFlow(
            type = listOf("Abs", "Chest")
        )

        override fun updateState(state: WorkoutAddingFlow) {
            this.state = state
        }

        override fun getState(): WorkoutAddingFlow {
            return checkNotNull(state)
        }

        override fun deleteState() {
            state = null
        }
    }

    @Test
    fun `When Submit button is clicked, insert ExerciseFLow into database and navigate to the HomeScreen`() {
        runTest {
            val viewModel = generateViewModel()
            val submitButton = TrackView.Action.GoToNextPage
            viewModel.onUiAction(submitButton)

            assertEquals(
                WorkoutAddingFlow(
                    type = listOf("Abs", "Chest"),
                    weight = 0,
                    reps = 0,
                    hours = 0,
                    mins = 0,
                    secs = 0,
                    comment = ""
                ),
                fakeTrackRepository.getExercise()
            )

            assertEquals(
                FakeNavigationBehaviour.Navigate(HomeDestination.route()),
                fakeNavigator.awaitNextScreen()
            )
        }
    }

    @Test
    fun `When the topbar back button is clicked, go back to previous screen`() {
        runTest {
            val viewModel = generateViewModel()
            val backButton = TrackView.Action.GoBackToPreviousPage
            viewModel.onUiAction(backButton)

            assertEquals(
                FakeNavigationBehaviour.NavigateUp,
                fakeNavigator.awaitNextScreen()
            )
        }
    }

    @Test
    fun `When minus counter in weight section is clicked, the value of weightNumber in TrackView State minus 1`() {
        runTest {
            val viewModel = generateViewModel()
            val onMinusCounterClicked = TrackView.Action.OnMinusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.WEIGHTTEXTFIELD
            )
            viewModel.onUiAction(onMinusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( weightNumber = "-1" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When minus counter in reps section is clicked, the value of repsNumber in TrackView State minus 1`() {
        runTest {
            val viewModel = generateViewModel()
            val onMinusCounterClicked = TrackView.Action.OnMinusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.REPSTEXTFIELD
            )
            viewModel.onUiAction(onMinusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( repsNumber = "-1" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When plus counter in weight section is clicked, the value of weightNumber in TrackView State plus 1`() {
        runTest {
            val viewModel = generateViewModel()
            val onPlusCounterClicked = TrackView.Action.OnPlusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.WEIGHTTEXTFIELD
            )
            viewModel.onUiAction(onPlusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( weightNumber = "1" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When plus counter in reps section is clicked, the value of repsNumber in TrackView State plus 1`() {
        runTest {
            val viewModel = generateViewModel()
            val onPlusCounterClicked = TrackView.Action.OnPlusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.REPSTEXTFIELD
            )
            viewModel.onUiAction(onPlusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( repsNumber = "1" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of TextFiled in weight section changes, update TrackView State weightNumber`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeWeightNumber("6")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( weightNumber = "6" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of TextFiled in reps section changes, update TrackView State repsNumber`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeRepsNumber("2")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( repsNumber = "2" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of hours TextFiled in time section changes, update TrackView State hours`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeHours("1")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( hours = "1" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of minutes TextFiled in time section changes, update TrackView State minutes`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeMinutes("32")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( minutes = "32" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of seconds TextFiled in time section changes, update TrackView State seconds`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeSeconds("28")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( seconds = "28" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of TextFiled in comment section changes, update TrackView State commentText`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeCommentText("Stretching")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( commentText = "Stretching" ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the clear button is clicked, update TrackView State commentText to empty string`() {
        runTest {
            val viewModel = generateViewModel()
            val clearButton = TrackView.Action.ClearComment
            viewModel.onUiAction(clearButton)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( commentText = "" ),
                    awaitItem()
                )
            }
        }
    }

    private fun generateViewModel() = TrackViewModel(fakeNavigator, fakeExerciseSharedStateManager, fakeTrackRepository)
}