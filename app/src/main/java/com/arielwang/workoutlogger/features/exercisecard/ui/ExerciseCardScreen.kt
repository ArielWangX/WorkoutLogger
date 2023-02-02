package com.arielwang.workoutlogger.features.exercisecard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.SectionTitle
import com.arielwang.workoutlogger.features.component.WorkoutLoggerTextField
import com.arielwang.workoutlogger.features.component.TextFieldType
import com.arielwang.workoutlogger.features.component.WorkoutLoggerScaffold
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun ExerciseCardScreen(
    uiState: ExerciseCardView.State = ExerciseCardView.State(),
    onAction: (ExerciseCardView.Action) -> Unit = {}
) {
    WorkoutLoggerScaffold(
        title = R.string.ExerciseDetailScreen_topbarTitle,
        iconContentDescription = R.string.ExerciseDetailScreen_topbarBackIconButton,
        onAction = { onAction(ExerciseCardView.Action.GoBackToPreviousPage) },
        actions = {
            IconButton(
                onClick = { onAction(ExerciseCardView.Action.SaveNewCard) },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.Done,
                    contentDescription = stringResource(id = R.string.ExerciseDetailScreen_topbarSaveIconButton)
                )
            }
        }
    ) {
        ExerciseCardScreenConstraintLayout(uiState = uiState, onAction = onAction)
    }
}

@Composable
fun ExerciseCardScreenConstraintLayout(
    uiState: ExerciseCardView.State,
    onAction: (ExerciseCardView.Action) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
    ) {
        val (section) = createRefs()

        SectionList(
            uiState = uiState,
            onAction = onAction,
            modifier = Modifier.constrainAs(section) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun SectionList(
    uiState: ExerciseCardView.State,
    onAction: (ExerciseCardView.Action) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        NameSection(uiState = uiState, onAction = onAction)
        NotesSection(uiState = uiState, onAction = onAction)
    }
}

@Composable
fun NameSection(
    uiState: ExerciseCardView.State,
    onAction: (ExerciseCardView.Action) -> Unit
) {
    SectionTextField(
        onAction = { onAction(ExerciseCardView.Action.ClearName) },
        title = stringResource(id = R.string.ExerciseDetailScreen_nameSection),
        textFieldValue = uiState.name,
        onValueChange = { onAction(ExerciseCardView.Action.OnTextFieldValueChangeNameText(it)) }
    )
}

@Composable
fun NotesSection(
    uiState: ExerciseCardView.State,
    onAction: (ExerciseCardView.Action) -> Unit
) {
    SectionTextField(
        onAction = { onAction(ExerciseCardView.Action.ClearNote) },
        title = stringResource(id = R.string.ExerciseDetailScreen_notesSection),
        textFieldValue = uiState.notes,
        onValueChange = { onAction(ExerciseCardView.Action.OnTextFieldValueChangeNoteText(it)) },
        imeAction = ImeAction.Done
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SectionTextField(
    onAction: () -> Unit,
    title: String,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    SectionTitle(
        text = title,
        modifier = Modifier.fillMaxWidth()
    ) {
        WorkoutLoggerTextField(
            onAction = onAction,
            value = textFieldValue,
            onValueChange = onValueChange,
            textAlign = TextAlign.Start,
            shape = MaterialTheme.shapes.large,
            type = TextFieldType.TEXT,
            keyboardType = KeyboardType.Text,
            imeAction = imeAction,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .navigationBarsWithImePadding()
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
    }
}