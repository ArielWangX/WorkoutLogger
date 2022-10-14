package com.arielwang.workoutlogger.features.track.ui.screen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.PrimaryButton

@Composable
fun TrackScreen(
    uiState: TrackView.State = TrackView.State(),
    onAction: (TrackView.Action) -> Unit
) {
    PrimaryButton(
        modifier = Modifier,
        buttonText = stringResource(id = R.string.TrackScreen_buttonContent),
        onAction = { onAction(TrackView.Action.GoToNextPage) }
    )

    NumberTextField()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NumberTextField(

) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = text,
        onValueChange = { text = it},
        textStyle = TextStyle.Default.copy(fontSize = 16.sp),
        colors = TextFieldDefaults
            .outlinedTextFieldColors(backgroundColor = MaterialTheme.colors.background),
        modifier = Modifier
            .height(52.dp)
            .width(80.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        )
    )
}
