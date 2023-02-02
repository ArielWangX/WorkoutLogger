package com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arielwang.workoutlogger.database.model.WorkoutData
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.domain.ExerciseRepository
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen.TrackDestination
import com.arielwang.workoutlogger.features.exercisecard.ui.ExerciseCardDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object ExerciseView {
    data class State(
        val cardList: List<Card> = emptyList()
    )

    sealed class Action {
        data class OnCardClicked(val text: String) : Action()
        object GoToNextPage : Action()
        object GoBackToPreviousPage : Action()
        object AddExerciseCard : Action()
    }
}

data class Card(
    val text: String = "",
    val isSelected: Boolean = false
)

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val navigator: Navigator,
    private val exerciseRepository: ExerciseRepository,
    private val exerciseSharedStateManager: ExerciseTrackSharedStateManager
) : ViewModel() {

    private var viewState = ExerciseView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<ExerciseView.State> = _uiState

    init {
        viewModelScope.launch {
            val getAllExerciseCards = exerciseRepository.getAllExerciseCards()

            viewState = viewState.copy(
                cardList = getAllExerciseCards.map {
                    Card(text = it.name)
                }
            )

            emitViewState()
        }
    }

    fun onUiAction(action: ExerciseView.Action) {
        when (action) {
            is ExerciseView.Action.GoToNextPage -> {
                exerciseSharedStateManager.updateState(
                    WorkoutData(
                        type = viewState.cardList.filter {
                            it.isSelected
                        }.map {
                            it.text
                        }
                    )
                )

                navigator.navigate(TrackDestination.route())
            }
            is ExerciseView.Action.GoBackToPreviousPage -> {
                navigator.navigateUp()
            }
            is ExerciseView.Action.OnCardClicked -> {
                viewState = viewState.copy(
                    cardList = viewState.cardList.map {
                        if (it.text == action.text) {
                            return@map it.copy(isSelected = !it.isSelected)
                        } else {
                            return@map it
                        }
                    }
                )
                emitViewState()
            }
            is ExerciseView.Action.AddExerciseCard -> {
                navigator.navigate(ExerciseCardDestination.route())
            }
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }
}
