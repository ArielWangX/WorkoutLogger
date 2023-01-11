package com.arielwang.workoutlogger.features.addexerciseflow.track.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arielwang.workoutlogger.database.model.ExerciseFlow
import com.arielwang.workoutlogger.features.addexerciseflow.shared.domain.ExerciseSharedStateManager
import com.arielwang.workoutlogger.features.addexerciseflow.track.domain.repository.TrackRepository
import com.arielwang.workoutlogger.features.home.ui.screen.HomeDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object TrackView {
    data class State(
        var weightNumber: String = "0",
        var repsNumber: String = "0",
        var hours: String = "",
        var minutes: String = "",
        var seconds: String = "",
        var commentText: String = ""
    )

    sealed class Action {
        data class onTextFieldValueChangeWeightNumber(val text: String) : Action()
        data class onTextFieldValueChangeRepsNumber(val text: String) : Action()
        data class onTextFieldValueChangeHours(val text: String) : Action()
        data class onTextFieldValueChangeMinutes(val text: String) : Action()
        data class onTextFieldValueChangeSeconds(val text: String) : Action()
        data class OnTextFieldValueChangeCommentText(val text: String) : Action()
        data class OnMinusCounterClick(val textFieldInWhichSection: TrackViewModel.TrackTextFieldInWhichSection) : Action()
        data class OnPlusCounterClick(val textFieldInWhichSection: TrackViewModel.TrackTextFieldInWhichSection) : Action()
        object ClearComment : Action()
        object GoToNextPage : Action()
        object GoBackToPreviousPage : Action()

    }
}

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val navigator: Navigator,
    private val exerciseSharedStateManager: ExerciseSharedStateManager,
    private val trackRepository: TrackRepository
) : ViewModel() {

    private var viewState = TrackView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<TrackView.State> = _uiState

    fun onUiAction(action: TrackView.Action) {
        when (action) {
            is TrackView.Action.GoToNextPage -> {
                viewModelScope.launch {
                    val types = exerciseSharedStateManager.getState().type
                    val exercise = ExerciseFlow(
                        type = types,
                        weight = viewState.weightNumber.toDouble(),
                        reps = viewState.repsNumber.toInt(),
                        hours = viewState.hours.toInt(),
                        mins = viewState.minutes.toInt(),
                        secs = viewState.seconds.toInt(),
                        comment = viewState.commentText
                    )
                    trackRepository.insertExercise(exercise)
                }
                navigator.navigate(HomeDestination.route())
            }

            is TrackView.Action.GoBackToPreviousPage -> {
                navigator.navigateUp()
            }

            is TrackView.Action.OnMinusCounterClick -> {
                when(action.textFieldInWhichSection) {
                    TrackTextFieldInWhichSection.WEIGHTTEXTFIELD -> {
                        viewState = viewState.copy(
                            weightNumber = (viewState.weightNumber.toDouble() - 1).toString()
                        )
                        emitViewState()
                    }
                    TrackTextFieldInWhichSection.REPSTEXTFIELD -> {
                        viewState = viewState.copy(
                            repsNumber = (viewState.repsNumber.toInt() - 1).toString()
                        )
                        emitViewState()
                    }
                }
            }

            is TrackView.Action.OnPlusCounterClick -> {
                when(action.textFieldInWhichSection) {
                    TrackTextFieldInWhichSection.WEIGHTTEXTFIELD -> {
                        viewState = viewState.copy(
                            weightNumber = (viewState.weightNumber.toDouble() + 1).toString()
                        )
                        emitViewState()
                    }
                    TrackTextFieldInWhichSection.REPSTEXTFIELD -> {
                        viewState = viewState.copy(
                            repsNumber = (viewState.repsNumber.toInt() + 1).toString()
                        )
                        emitViewState()
                    }
                }
            }

            is TrackView.Action.onTextFieldValueChangeWeightNumber -> {
                viewState = viewState.copy(
                    weightNumber = action.text
                )
                emitViewState()
            }

            is TrackView.Action.onTextFieldValueChangeRepsNumber -> {
                viewState = viewState.copy(
                    repsNumber = action.text
                )
                emitViewState()
            }

            is TrackView.Action.onTextFieldValueChangeHours -> {
                viewState = viewState.copy(
                    hours = action.text
                )
                emitViewState()
            }

            is TrackView.Action.onTextFieldValueChangeMinutes -> {
                viewState = viewState.copy(
                    minutes = action.text
                )
                emitViewState()
            }

            is TrackView.Action.onTextFieldValueChangeSeconds -> {
                viewState = viewState.copy(
                    seconds = action.text
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeCommentText -> {
                viewState = viewState.copy(
                    commentText = action.text
                )
                emitViewState()
            }

            is TrackView.Action.ClearComment -> {
                viewState = viewState.copy(
                    commentText = ""
                )
                emitViewState()
            }
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }

    enum class TrackTextFieldInWhichSection {
        WEIGHTTEXTFIELD, REPSTEXTFIELD
    }
}