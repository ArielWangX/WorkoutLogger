package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.features.exercise.ui.screen.ExerciseDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

object HomeView {
    data class State(
        val workoutForToday : Boolean = false
    )

    sealed class Action {
        object GoToNextPage : Action()
    }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private var viewState = HomeView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<HomeView.State> = _uiState

    fun onUiAction(action: HomeView.Action) {
        when(action) {
            HomeView.Action.GoToNextPage -> navigator.navigate(ExerciseDestination.route())
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }

}