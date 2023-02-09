package com.arielwang.workoutlogger.track

import app.cash.turbine.test
import com.arielwang.workoutlogger.database.model.WorkoutData
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import com.arielwang.workoutlogger.features.workoutaddingflow.track.domain.repository.TrackRepository
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.TrackView
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.TrackViewModel
import com.arielwang.workoutlogger.features.home.ui.screen.HomeDestination
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.ShouldShowMaxCharAlert
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
        private var workoutData: WorkoutData? = null

        override suspend fun insertWorkout(workoutData: WorkoutData) {
            this.workoutData = workoutData
        }

        fun getWorkoutData(): WorkoutData { return checkNotNull(workoutData) }
    }

    private val fakeExerciseSharedStateManager = object : ExerciseTrackSharedStateManager {
        private var state: WorkoutData? = WorkoutData(
            type = listOf("Abs", "Chest")
        )

        override fun updateState(state: WorkoutData) {
            this.state = state
        }

        override fun getState(): WorkoutData {
            return checkNotNull(state)
        }
    }

    @Test
    fun `When Submit button is clicked, insert ExerciseFLow into database and navigate to the HomeScreen`() {
        runTest {
            val viewModel = generateViewModel()
            val submitButton = TrackView.Action.GoToNextPage
            viewModel.onUiAction(submitButton)

            assertEquals(
                WorkoutData(
                    type = listOf("Abs", "Chest"),
                    weight = 0,
                    reps = 0,
                    hours = 0,
                    mins = 0,
                    secs = 0,
                    comment = ""
                ),
                fakeTrackRepository.getWorkoutData()
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

    // check setDefaultTextFieldValue function in weightSection TextFiled
    @Test
    fun `When weightSection TextField value is an empty string and minus counter in weight section is clicked, the value of weightNumber in TrackView State is 0`() {
        runTest {
            val viewModel = generateViewModel()
            val onMinusCounterClicked = TrackView.Action.OnMinusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.WEIGHTTEXTFIELD
            )
            viewModel.onUiAction(onMinusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( weightNumber = "0" ),
                    awaitItem()
                )
            }
        }
    }

    // The minimum value of weightSection TextFiled is 0
    @Test
    fun `When weightSection TextField value is 0 and minus counter in weight section is clicked, the value of weightNumber in TrackView State is 0`() {
        runTest {
            val viewModel = generateViewModel()
            val onMinusCounterClicked = TrackView.Action.OnMinusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.WEIGHTTEXTFIELD
            )
            // First onMinusCounterClicked action will set default value of TextField as 0
            viewModel.onUiAction(onMinusCounterClicked)
            // Second onMinusCounterClicked action will calculate the math of 0 minus 1
            viewModel.onUiAction(onMinusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( weightNumber = "0" ),
                    awaitItem()
                )
            }
        }
    }

    // check setDefaultTextFieldValue function in repsSection TextFiled
    @Test
    fun `When repsSection TextField value is an empty string and minus counter in reps section is clicked, the value of repsNumber in TrackView State minus 0`() {
        runTest {
            val viewModel = generateViewModel()
            val onMinusCounterClicked = TrackView.Action.OnMinusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.REPSTEXTFIELD
            )
            viewModel.onUiAction(onMinusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( repsNumber = "0" ),
                    awaitItem()
                )
            }
        }
    }

    // The minimum value of repsSection TextFiled is 0
    @Test
    fun `When repsSection TextField value is 0 and minus counter in reps section is clicked, the value of repsNumber in TrackView State minus 0`() {
        runTest {
            val viewModel = generateViewModel()
            val onMinusCounterClicked = TrackView.Action.OnMinusCounterClick(
                TrackViewModel.TrackTextFieldInWhichSection.REPSTEXTFIELD
            )

            // First onMinusCounterClicked action will set default value of TextField as 0
            viewModel.onUiAction(onMinusCounterClicked)
            // Second onMinusCounterClicked action will calculate the math of 0 minus 1
            viewModel.onUiAction(onMinusCounterClicked)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State( repsNumber = "0" ),
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
    fun `When the value of TextFiled in weightSection changes, update TrackView State weightNumber`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeWeightNumber("6")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        weightNumber = "6",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            maxChar = 3
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    // Check maximum input number of characters in weightSection TextField
    @Test
    fun `Maximum input number of characters in weightSection TextField is 3`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeWeightNumber("6789")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        weightNumber = "678",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            shouldShow = true,
                            maxChar = 3
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of TextFiled in repsSection changes, update TrackView State repsNumber`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeRepsNumber("2")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        repsNumber = "2",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            maxChar = 4
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    // Check maximum input number of characters in repsSection TextField
    @Test
    fun `Maximum input number of characters in repsSection TextField is 4`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeRepsNumber("22345")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        repsNumber = "2234",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            shouldShow = true,
                            maxChar = 4
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of hours TextFiled in timeSection changes, update TrackView State hours`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeHours("1")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        hours = "1",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            maxChar = 2
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    // Check maximum input number of characters in hours TextField
    @Test
    fun `Maximum input number of characters in hours TextField of timeSection is 2`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeHours("102")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        hours = "10",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            shouldShow = true,
                            maxChar = 2
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of minutes TextFiled in timeSection changes, update TrackView State minutes`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeMinutes("32")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        minutes = "32",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            maxChar = 2
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    // Check maximum input number of characters in minutes TextField
    @Test
    fun `Maximum input number of characters in minutes TextField of timeSection is 2`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeMinutes("324")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        minutes = "32",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            shouldShow = true,
                            maxChar = 2
                        )
                    ),
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
                    TrackView.State(
                        seconds = "28",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            maxChar = 2
                        )
                    ),
                    awaitItem()
                )
            }
        }
    }

    // Check maximum input number of characters in seconds TextField
    @Test
    fun `Maximum input number of characters in seconds TextField of timeSection is 2`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextFieldValueChange = TrackView.Action.OnTextFieldValueChangeSeconds("289")
            viewModel.onUiAction(onTextFieldValueChange)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        seconds = "28",
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert(
                            shouldShow = true,
                            maxChar = 2
                        )
                    ),
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

    @Test
    fun changeShouldShowMaxCharState() {
        runTest {
            val viewModel = generateViewModel()
            val changeShouldShowMaxCharState = TrackView.Action.ChangeShouldShowMaxCharState( shouldShow = false)

            viewModel.onUiAction(changeShouldShowMaxCharState)

            viewModel.uiState.test {
                assertEquals(
                    TrackView.State(
                        shouldShowMaxCharAlert = ShouldShowMaxCharAlert( shouldShow = false )
                    ),
                    awaitItem()
                )
            }
        }
    }

    private fun generateViewModel() = TrackViewModel(fakeNavigator, fakeExerciseSharedStateManager, fakeTrackRepository)
}