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
        val weightNumber: TextFieldProperty = TextFieldProperty(text = ""),
        val repsNumber: TextFieldProperty = TextFieldProperty(text = ""),
        val hours: TextFieldProperty = TextFieldProperty(text = ""),
        val minutes: TextFieldProperty = TextFieldProperty(text = ""),
        val seconds: TextFieldProperty = TextFieldProperty(text = ""),
        val commentText: String = ""
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

        object ClearComment : Action()
        object GoToNextPage : Action()
        object GoBackToPreviousPage : Action()

    }
}

data class TextFieldProperty(
    var text: String = "",
    var maxChar: Boolean = false
)

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
                        weight = setDefaultTextFieldValue(viewState.weightNumber.text).toInt(),
                        reps = setDefaultTextFieldValue(viewState.repsNumber.text).toInt(),
                        hours = setDefaultTextFieldValue(viewState.hours.text).toInt(),
                        mins = setDefaultTextFieldValue(viewState.minutes.text).toInt(),
                        secs = setDefaultTextFieldValue(viewState.seconds.text).toInt(),
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
                when (action.textFieldInWhichSection) {
                    TrackTextFieldInWhichSection.WEIGHTTEXTFIELD -> {
                        viewState = viewState.copy(
                            weightNumber = TextFieldProperty(
                                text = noNegativeNumber(
                                    setDefaultTextFieldValue(viewState.weightNumber.text)
                                )
                            )
                        )
                        emitViewState()
                    }
                    TrackTextFieldInWhichSection.REPSTEXTFIELD -> {
                        viewState = viewState.copy(
                            repsNumber = TextFieldProperty(
                                text = noNegativeNumber(
                                    setDefaultTextFieldValue(viewState.repsNumber.text)
                                )
                            )
                        )
                        emitViewState()
                    }
                }
            }

            is TrackView.Action.OnPlusCounterClick -> {
                when (action.textFieldInWhichSection) {
                    TrackTextFieldInWhichSection.WEIGHTTEXTFIELD -> {
                        viewState = viewState.copy(
                            weightNumber = TextFieldProperty(
                                text = (
                                        setDefaultTextFieldValue(viewState.weightNumber.text).toInt() + 1
                                        ).toString()
                            )
                        )
                        emitViewState()
                    }
                    TrackTextFieldInWhichSection.REPSTEXTFIELD -> {
                        viewState = viewState.copy(
                            repsNumber = TextFieldProperty(
                                text = (
                                        setDefaultTextFieldValue(viewState.repsNumber.text).toInt() + 1
                                        ).toString()
                            )
                        )
                        emitViewState()
                    }
                }
            }

            is TrackView.Action.OnTextFieldValueChangeWeightNumber -> {
                viewState = viewState.copy(
                    weightNumber = maximumTextFieldInput(
                        setDefaultTextFieldValue(action.text),
                        3
                    )
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeRepsNumber -> {
                viewState = viewState.copy(
                    repsNumber = maximumTextFieldInput(
                        setDefaultTextFieldValue(action.text),
                        4
                    )
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeHours -> {
                viewState = viewState.copy(
                    hours = maximumTextFieldInput(action.text, 2)
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeMinutes -> {
                viewState = viewState.copy(
                    minutes = maximumTextFieldInput(action.text, 2)
                )
                emitViewState()
            }

            is TrackView.Action.OnTextFieldValueChangeSeconds -> {
                viewState = viewState.copy(
                    seconds = maximumTextFieldInput(action.text, 2)
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

    // the value of TextField cannot be negative
    private fun noNegativeNumber(text: String): String {
        return if ((text.toInt() - 1) < 0) {
            "0"
        } else {
            (text.toInt() - 1).toString()
        }
    }

    // Set maximum input length of TextField
    private fun maximumTextFieldInput(
        text: String,
        maxChar: Int
    ): TextFieldProperty {
        val validateInput = TextFieldProperty(text = "")
        val textStartedFromNonZero = text.dropWhile { it.toString() == "0" }

        if (text.length <= maxChar) {
            validateInput.text = textStartedFromNonZero
        } else {
            validateInput.text = textStartedFromNonZero.slice(0 until maxChar)
            validateInput.maxChar = !validateInput.maxChar
        }

        return validateInput
    }

    // if the value of TextField is "", set default value "0" for calculation
    private fun setDefaultTextFieldValue(text: String): String {
        return if (text == "") "0" else text
    }

    enum class TrackTextFieldInWhichSection {
        WEIGHTTEXTFIELD, REPSTEXTFIELD
    }
}