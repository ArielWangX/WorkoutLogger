package com.arielwang.workoutlogger.features.workoutaddingflow.track.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arielwang.workoutlogger.database.model.WorkoutData
import com.arielwang.workoutlogger.features.workoutaddingflow.shared.domain.ExerciseTrackSharedStateManager
import com.arielwang.workoutlogger.features.workoutaddingflow.track.domain.repository.TrackRepository
import com.arielwang.workoutlogger.features.home.ui.screen.HomeDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object TrackView {
    data class State(
        val weightNumber: String = "",
        val repsNumber: String = "",
        val hours: String = "",
        val minutes: String = "",
        val seconds: String = "",
        val commentText: String = "",
        val shouldShowMaxCharAlert: ShouldShowMaxCharAlert = ShouldShowMaxCharAlert()
    )

    sealed class Action {
        data class OnTextFieldValueChangeWeightNumber(val text: String) : Action()
        data class OnTextFieldValueChangeRepsNumber(val text: String) : Action()
        data class OnTextFieldValueChangeHours(val text: String) : Action()
        data class OnTextFieldValueChangeMinutes(val text: String) : Action()
        data class OnTextFieldValueChangeSeconds(val text: String) : Action()
        data class OnTextFieldValueChangeCommentText(val text: String) : Action()
        data class OnMinusCounterClick(val textFieldInWhichSection: TrackViewModel.TrackTextFieldInWhichSection) :
            Action()

        data class OnPlusCounterClick(val textFieldInWhichSection: TrackViewModel.TrackTextFieldInWhichSection) :
            Action()

        data class ChangeShouldShowMaxCharState(val shouldShow: Boolean) : Action()

        object ClearComment : Action()
        object GoToNextPage : Action()
        object GoBackToPreviousPage : Action()

    }
}

data class ShouldShowMaxCharAlert(
    var shouldShow: Boolean = false,
    var maxChar: Int = 0
)

data class ValidTextAndAlertState(
    var validText: String,
    var shouldShowMaxCharAlert: ShouldShowMaxCharAlert
)

@HiltViewModel
class TrackViewModel @Inject constructor(
    private val navigator: Navigator,
    private val exerciseSharedStateManager: ExerciseTrackSharedStateManager,
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

                    val workoutData = WorkoutData(
                        type = types,
                        weight = setDefaultTextFieldValue(viewState.weightNumber).toInt(),
                        reps = setDefaultTextFieldValue(viewState.repsNumber).toInt(),
                        hours = setDefaultTextFieldValue(viewState.hours).toInt(),
                        mins = setDefaultTextFieldValue(viewState.minutes).toInt(),
                        secs = setDefaultTextFieldValue(viewState.seconds).toInt(),
                        comment = viewState.commentText
                    )
                    trackRepository.insertWorkout(workoutData)
                }
                navigator.navigate(HomeDestination.route())
            }

            is TrackView.Action.GoBackToPreviousPage -> {
                navigator.navigateUp()
            }

            is TrackView.Action.OnMinusCounterClick -> {
                when (action.textFieldInWhichSection) {
                    TrackTextFieldInWhichSection.WEIGHTTEXTFIELD -> {
                        viewState = viewState.copy(
                            weightNumber = (
                                setDefaultTextFieldValue(viewState.weightNumber).toInt() - 1
                            ).coerceAtLeast(0).toString()
                        )
                        emitViewState()
                    }
                    TrackTextFieldInWhichSection.REPSTEXTFIELD -> {
                        viewState = viewState.copy(
                            repsNumber = (
                                setDefaultTextFieldValue(viewState.repsNumber).toInt() - 1
                            ).coerceAtLeast(0).toString()
                        )
                        emitViewState()
                    }
                }
            }

            is TrackView.Action.OnPlusCounterClick -> {
                when (action.textFieldInWhichSection) {
                    TrackTextFieldInWhichSection.WEIGHTTEXTFIELD -> {
                        viewState = viewState.copy(
                            weightNumber = (setDefaultTextFieldValue(viewState.weightNumber).toInt() + 1).toString()
                        )
                        emitViewState()
                    }
                    TrackTextFieldInWhichSection.REPSTEXTFIELD -> {
                        viewState = viewState.copy(
                            repsNumber = (setDefaultTextFieldValue(viewState.repsNumber).toInt() + 1).toString()
                        )
                        emitViewState()
                    }
                }
            }

            is TrackView.Action.OnTextFieldValueChangeWeightNumber -> {
                val onTextFieldChange = maximumInputLengthInTextField(
                    setDefaultTextFieldValue(action.text),
                    3
                )
                viewState = viewState.copy(
                    weightNumber = onTextFieldChange.validText,
                    shouldShowMaxCharAlert = onTextFieldChange.shouldShowMaxCharAlert
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeRepsNumber -> {
                val onTextFieldChange = maximumInputLengthInTextField(
                    setDefaultTextFieldValue(action.text),
                    4
                )
                viewState = viewState.copy(
                    repsNumber = onTextFieldChange.validText,
                    shouldShowMaxCharAlert = onTextFieldChange.shouldShowMaxCharAlert
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeHours -> {
                val onTextFieldChange = maximumInputLengthInTextField(action.text, 2)

                viewState = viewState.copy(
                    hours = onTextFieldChange.validText,
                    shouldShowMaxCharAlert = onTextFieldChange.shouldShowMaxCharAlert
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeMinutes -> {
                val onTextFieldChange = maximumInputLengthInTextField(action.text, 2)

                viewState = viewState.copy(
                    minutes = onTextFieldChange.validText,
                    shouldShowMaxCharAlert = onTextFieldChange.shouldShowMaxCharAlert
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeSeconds -> {
                val onTextFieldChange = maximumInputLengthInTextField(action.text, 2)

                viewState = viewState.copy(
                    seconds = onTextFieldChange.validText,
                    shouldShowMaxCharAlert = onTextFieldChange.shouldShowMaxCharAlert
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
            is TrackView.Action.ChangeShouldShowMaxCharState -> {
                viewState = viewState.copy(
                    shouldShowMaxCharAlert = ShouldShowMaxCharAlert(shouldShow = action.shouldShow)
                )
                emitViewState()
            }
        }
    }

    private fun emitViewState() {
        _uiState.value = viewState
    }

    // Adding a condition to limit maximum input length in TextField
    // If the length of user input is less or equal than maximum input length, input stay the same.
    // Else, slice the user input length to maximum input length and change the value of shouldShow to true.
    private fun maximumInputLengthInTextField(
        text: String,
        maxChar: Int
    ): ValidTextAndAlertState {
        val validInput: String
        var shouldShow = false
        val textStartedFromNonZero = text.dropWhile { it.toString() == "0" }

        if (text.length <= maxChar) {
            validInput = textStartedFromNonZero
        } else {
            validInput = textStartedFromNonZero.slice(0 until maxChar)
            shouldShow = true
        }

        return ValidTextAndAlertState(
            validText = validInput,
            shouldShowMaxCharAlert = ShouldShowMaxCharAlert(shouldShow, maxChar)
        )
    }

    // if the value of TextField is "", set default value "0" for calculation
    private fun setDefaultTextFieldValue(text: String): String {
        return if (text == "") "0" else text
    }

    enum class TrackTextFieldInWhichSection {
        WEIGHTTEXTFIELD, REPSTEXTFIELD
    }
}