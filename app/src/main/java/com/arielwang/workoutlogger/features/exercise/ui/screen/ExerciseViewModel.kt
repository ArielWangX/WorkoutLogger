package com.arielwang.workoutlogger.features.exercise.ui.screen

import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.features.home.ui.screen.HomeDestination
import com.arielwang.workoutlogger.features.landing.ui.screen.LandingDestination
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
    private val navigator: Navigator
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
                navigator.navigate(LandingDestination.route())
            }
            is ExerciseView.Action.GoBackToPreviousPage -> {
                navigator.navigate(HomeDestination.route())
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

//  different approach
//                val updatedCardList = mutableListOf<Card>()
//
//                viewState.cardList.forEach {
//                    if (action.text == it.text) {
//                        updatedCardList.add(it.copy(isSelected = !it.isSelected))
//                    } else {
//                        updatedCardList.add(it)
//                    }
//                }
//
//                viewState = viewState.copy(
//                    cardList = updatedCardList
//                )

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