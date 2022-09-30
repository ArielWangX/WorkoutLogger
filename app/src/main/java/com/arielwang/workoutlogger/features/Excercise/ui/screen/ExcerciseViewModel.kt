package com.arielwang.workoutlogger.features.Excercise.ui.screen

import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.landing.ui.screen.LandingDestination
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

object ExcerciseView {
    data class State(
        val cardList: List<Card> = emptyList()
    )

    sealed class Action {
        data class OnCardClicked(val text: String) : Action()
        object GoToNextPage : Action()
    }
}

data class Card(
    val text: String = "",
    val isSelected: Boolean = false
)

@HiltViewModel
class ExcerciseViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private var viewState = ExcerciseView.State()

    private val _uiState = MutableStateFlow(viewState)
    val uiState: StateFlow<ExcerciseView.State> = _uiState

    init {
        viewState = viewState.copy(
            cardList =
            listOf(Card("abs"), Card("Back"), Card("Biceps"), Card("Cardio"), Card("Chest"))
        )
        emitViewState()
    }

    fun onUiAction(action: ExcerciseView.Action) {
        when (action) {
            is ExcerciseView.Action.GoToNextPage -> {
                navigator.navigate(LandingDestination.route())
            }
            is ExcerciseView.Action.OnCardClicked -> {
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

//val excerciseCardData = listOf(
//    R.string.ExcerciseCard_abs,
//    R.string.ExcerciseCard_back,
//    R.string.ExcerciseCard_biceps,
//    R.string.ExcerciseCard_cardio,
//    R.string.ExcerciseCard_chest
//)