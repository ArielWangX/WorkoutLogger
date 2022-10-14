package com.arielwang.workoutlogger.features.track.ui.screen

import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.features.exercise.ui.screen.ExerciseDestination
import com.arielwang.workoutlogger.features.home.ui.screen.HomeDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

object TrackView {
    data class State(
        val notKnow: String = ""
    )

    sealed class Action {
        object GoToNextPage : Action()
        object GoBackToPreviousPage : Action()
    }
}

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private var viewState = TrackView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<TrackView.State> = _uiState

    fun onUiAction(action: TrackView.Action) {
        when (action) {
            is TrackView.Action.GoToNextPage -> {
                navigator.navigate(HomeDestination.route())
            }
            is TrackView.Action.GoBackToPreviousPage -> {
                navigator.navigateUp()
            }
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }
}