package com.arielwang.workoutlogger.features.addexerciseflow.exercise.ui.screen

import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.addexerciseflow.shared.domain.ExerciseSharedStateManager
import com.arielwang.workoutlogger.features.addexerciseflow.track.ui.screen.TrackDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

object ExerciseView {
    data class State(
        val cardList: List<Card> = emptyList()
    )

    sealed class Action {
        data class OnCardClicked(val text: String) : Action()
        object GoToNextPage : Action()
        object GoBackToPreviousPage : Action()
    }
}

data class Card(
    val text: String = "",
    val isSelected: Boolean = false
)

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val navigator: Navigator,
    private val exerciseSharedStateManager: ExerciseSharedStateManager
) : ViewModel() {

    private var viewState = ExerciseView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<ExerciseView.State> = _uiState

    init {
        viewState = viewState.copy(
            cardList =
            listOf(
                Card("abs"), Card("Back"), Card("Biceps"), Card("Cardio"), Card("Chest"),
                Card("sdf"), Card("werfwec"), Card("sdfs"), Card("efw"), Card("ef"),
                Card("srfgs"), Card("hgf"), Card("ert"), Card("g"), Card("ert")
            )
        )
        emitViewState()
    }

    fun onUiAction(action: ExerciseView.Action) {
        when (action) {
            is ExerciseView.Action.GoToNextPage -> {
                exerciseSharedStateManager.updateState(
                    ExerciseFlow(
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
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }
}
