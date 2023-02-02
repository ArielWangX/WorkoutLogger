package com.arielwang.workoutlogger.features.home.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arielwang.workoutlogger.features.workoutaddingflow.exercise.ui.screen.ExerciseDestination
import com.arielwang.workoutlogger.features.home.domain.repository.HomeRepository
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object HomeView {
    data class State(
        val workoutForToday : Boolean = false,
        val exercises: List<String> = emptyList()
    )

    sealed class Action {
        object GoToNextPage : Action()
    }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private var viewState = HomeView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<HomeView.State> = _uiState

    init {
        viewModelScope.launch {
            val exercises = homeRepository.getAllWorkoutData()
            val exercisesString = exercises.map {
                "${it.type} \n" +
                "${it.weight}, ${it.reps}\n ${it.hours}h ${it.mins}min ${it.secs}secs \n ${it.comment}"
            }

            viewState = viewState.copy(exercises = exercisesString)
            emitViewState()
        }
    }

    fun onUiAction(action: HomeView.Action) {
        when(action) {
            HomeView.Action.GoToNextPage -> navigator.navigate(ExerciseDestination.route())
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }
}