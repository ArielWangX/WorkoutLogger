package com.arielwang.workoutlogger.exercisecard

import app.cash.turbine.test
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.features.exercisecard.domain.ExerciseCardRepository
import com.arielwang.workoutlogger.features.exercisecard.ui.ExerciseCardView
import com.arielwang.workoutlogger.features.exercisecard.ui.ExerciseCardViewModel
import com.arielwang.workoutlogger.navigate.FakeNavigationBehaviour
import com.arielwang.workoutlogger.navigate.FakeNavigatorRule
import com.arielwang.workoutlogger.testutils.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ExerciseCardViewModelTest {
    @get:Rule
    val navigatorRule = FakeNavigatorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val fakeNavigator = navigatorRule.navigator
    private val fakeExerciseCardRepository = object : ExerciseCardRepository {
        private var storingExerciseCard = ExerciseCard()

        override suspend fun insertExerciseCard(exerciseCard: ExerciseCard) {
            storingExerciseCard = ExerciseCard(name = exerciseCard.name, note = exerciseCard.note)
        }

        fun getExerciseCard(): ExerciseCard {
            return storingExerciseCard
        }

        override suspend fun updateExerciseCard(exerciseCard: ExerciseCard) {
            TODO("Not yet implemented")
        }

        override suspend fun deleteExerciseCard(exerciseCard: ExerciseCard) {
            TODO("Not yet implemented")
        }

    }

    @Test
    fun `When topbar back button is clicked, go back to previous page`() {
        runTest {
            val viewModel = generateViewModel()
            val goBackToPreviousPageButton = ExerciseCardView.Action.GoBackToPreviousPage

            viewModel.onUiAction(goBackToPreviousPageButton)

            assertEquals(
                FakeNavigationBehaviour.NavigateUp,
                fakeNavigator.awaitNextScreen()
            )
        }
    }

    @Test
    fun `When the value of TextFiled in nameSection changes, update ExerciseCardView State name`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextChange = ExerciseCardView.Action.OnTextFieldValueChangeNameText("Core")

            viewModel.onUiAction(onTextChange)

            viewModel.uiState.test {
                assertEquals(
                    ExerciseCardView.State(
                        name = "Core"
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When the value of TextFiled in notesSection changes, update ExerciseCardView State notes`() {
        runTest {
            val viewModel = generateViewModel()
            val onTextChange = ExerciseCardView.Action.OnTextFieldValueChangeNoteText("Repeat twice")

            viewModel.onUiAction(onTextChange)

            viewModel.uiState.test {
                assertEquals(
                    ExerciseCardView.State(
                        notes = "Repeat twice"
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When clear button in nameSection is clicked, update ExerciseCardView State name`() {
        runTest {
            val viewModel = generateViewModel()
            val onClearButtonClicked = ExerciseCardView.Action.ClearName

            viewModel.onUiAction(onClearButtonClicked)

            viewModel.uiState.test {
                assertEquals(
                    ExerciseCardView.State(
                        name = ""
                    ),
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `When clear button in notesSection is clicked, update ExerciseCardView State notes`() {
        runTest {
            val viewModel = generateViewModel()
            val onClearButtonClicked = ExerciseCardView.Action.ClearNote

            viewModel.onUiAction(onClearButtonClicked)

            viewModel.uiState.test {
                assertEquals(
                    ExerciseCardView.State(
                        notes = ""
                    ),
                    awaitItem()
                )
            }
        }
    }

    // when saved button is clicked, insert exercise card data into database and go back to Exercise screen
    @Test
    fun saveNewExerciseCard() {
        runTest {
            val viewModel = generateViewModel()
            val onSaveButtonClicked = ExerciseCardView.Action.SaveNewCard

            viewModel.onUiAction(onSaveButtonClicked)

            assertEquals(
                ExerciseCard(name = "", note = ""),
                fakeExerciseCardRepository.getExerciseCard()
            )

            assertEquals(
                FakeNavigationBehaviour.NavigateUp,
                fakeNavigator.awaitNextScreen()
            )
        }
    }

    private fun generateViewModel() = ExerciseCardViewModel(fakeNavigator, fakeExerciseCardRepository)
}