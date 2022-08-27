package com.arielwang.workoutlogger.features.Excercise.ui.screen

import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.features.landing.ui.screen.LandingDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

object ExcerciseView {
    data class State(
        val workoutForToday: Boolean = false
    )

    sealed class Action {
        object GoToNextPage : Action()
    }
}

@HiltViewModel
class ExcerciseViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private var viewState = ExcerciseView.State()

    private val  _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<ExcerciseView.State> = _uiState

    fun onUiAction(action: ExcerciseView.Action) {
        when(action) {
            ExcerciseView.Action.GoToNextPage -> {navigator.navigate(LandingDestination.route())}
        }
    }
}