package com.arielwang.workoutlogger.features.exercisecard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arielwang.workoutlogger.database.model.ExerciseCard
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen.ExerciseDestination
import com.arielwang.workoutlogger.features.exercisecard.domain.ExerciseCardRepository
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object ExerciseCardView {
    data class State(
        val name: String = "",
        val notes: String = ""
    )

    sealed class Action {
        data class OnTextFieldValueChangeNameText(val text: String) : Action()
        data class OnTextFieldValueChangeNoteText(val text: String) : Action()
        object GoBackToPreviousPage : Action()
        object ClearName : Action()
        object ClearNote : Action()
        object SaveNewCard : Action()
    }
}

@HiltViewModel
class ExerciseCardViewModel @Inject constructor(
    private val navigator: Navigator,
    private val exerciseCardRepository: ExerciseCardRepository
) : ViewModel() {

    private var viewState = ExerciseCardView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<ExerciseCardView.State> = _uiState

    fun onUiAction(action: ExerciseCardView.Action) {
        when (action) {
            is ExerciseCardView.Action.GoBackToPreviousPage -> {
                navigator.navigateUp()
            }
            is ExerciseCardView.Action.OnTextFieldValueChangeNameText -> {
                viewState = viewState.copy(
                    name = action.text
                )
                emitViewState()
            }
            is ExerciseCardView.Action.OnTextFieldValueChangeNoteText -> {
                viewState = viewState.copy(
                    notes = action.text
                )
                emitViewState()
            }
            is ExerciseCardView.Action.ClearName -> {
                viewState = viewState.copy(
                    name = ""
                )
                emitViewState()
            }
            is ExerciseCardView.Action.ClearNote -> {
                viewState = viewState.copy(
                    notes = ""
                )
                emitViewState()
            }
            is ExerciseCardView.Action.SaveNewCard -> {
                val newExerciseCard = ExerciseCard(
                    name = viewState.name,
                    note = viewState.notes
                )

                viewModelScope.launch {
                    exerciseCardRepository.insertExerciseCard(newExerciseCard)
                }

                navigator.navigateUp()
            }
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }
}