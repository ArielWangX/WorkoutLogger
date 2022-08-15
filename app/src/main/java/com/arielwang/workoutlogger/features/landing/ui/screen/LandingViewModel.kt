package com.arielwang.workoutlogger.features.landing.ui.screen

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import com.arielwang.workoutlogger.navigate.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

object LandingView {
  data class State(
    val currentTime: String = ""
  )

  sealed class Action {
    object GoToNextPage : Action()
  }
}

@HiltViewModel
class LandingViewModel @Inject constructor(
  private val navigator: Navigator
) : ViewModel() {


  private var viewState = LandingView.State()

  private val _uiState = MutableStateFlow(viewState)
  val uiState: StateFlow<LandingView.State> = _uiState

  init {
    val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      .format(Calendar.getInstance().time)
    viewState = viewState.copy(currentTime = currentTime)
    emitViewState()
  }


  fun onUiAction(action: LandingView.Action) {
    when (action) {
      LandingView.Action.GoToNextPage -> {}
    }
  }

  private fun emitViewState() {
    _uiState.value = viewState
  }

}