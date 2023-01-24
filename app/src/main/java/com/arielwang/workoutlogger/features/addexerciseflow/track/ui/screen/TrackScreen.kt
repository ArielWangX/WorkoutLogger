package com.arielwang.workoutlogger.features.addexerciseflow.track.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.PrimaryButton
import com.arielwang.workoutlogger.features.component.ScaffoldWithTopBar
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun TrackScreen(
    uiState: TrackView.State = TrackView.State(),
    onAction: (TrackView.Action) -> Unit
) {
    ScaffoldWithTopBar(
        title = R.string.TrackScreen_topbarTitle,
        iconContentDescription = R.string.TrackScreen_topbarIconButtonContentDescription,
        onAction = { onAction(TrackView.Action.GoBackToPreviousPage) },
        modifier = Modifier.systemBarsPadding()
    ) {
        TrackScreenConstraintLayout(
            uiState = uiState,
            onAction = onAction
        )
    }
}

@Composable
fun TrackScreenConstraintLayout(
    uiState: TrackView.State,
    onAction: (TrackView.Action) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (button, sectionList) = createRefs()

        SectionList(
            uiState = uiState,
            onAction = onAction,
            modifier = Modifier.constrainAs(sectionList) {
                linkTo(parent.top, button.top, bias = 0f)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        PrimaryButton(
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom)
            },
            buttonText = stringResource(id = R.string.TrackScreen_buttonContent),
            onAction = { onAction(TrackView.Action.GoToNextPage) }
        )
    }
}

@Composable
fun SectionList(
    uiState: TrackView.State,
    onAction: (TrackView.Action) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        WeightSection(uiState = uiState, onAction = onAction)
        RepsSection(uiState = uiState, onAction = onAction)
        TimeSection(uiState = uiState, onAction = onAction)
        CommentSection(uiState = uiState, onAction = onAction)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentSection(
    uiState: TrackView.State,
    onAction: (TrackView.Action) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    SectionTitle(
        text = stringResource(R.string.TrackScreen_commentSection),
        modifier = Modifier.fillMaxWidth()
    ) {
        TrackTextField(
            onAction = onAction,
            value = uiState.commentText,
            onValueChange = { onAction(TrackView.Action.OnTextFieldValueChangeCommentText(it)) },
            placeHolderText = stringResource(id = R.string.TrackScreen_commentSection_placeHolderText),
            textAlign = TextAlign.Start,
            shape = MaterialTheme.shapes.large,
            type = TrackTextFieldType.TEXT,
            singleLine = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
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

@Composable
fun TimeSection(
    uiState: TrackView.State,
    onAction: (TrackView.Action) -> Unit
) {
    SectionTitle(
        text = stringResource(R.string.TrackScreen_timeSection),
        modifier = Modifier
    ) {
        TimeTextFieldGroup(uiState = uiState, onAction = onAction)
    }
}

@Composable
fun RepsSection(
    uiState: TrackView.State,
    onAction: (TrackView.Action) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    SectionTitle(
        text = stringResource(R.string.TrackScreen_repsSection),
        modifier = Modifier.fillMaxWidth()
    ) {
        CounterAndTextField(
            onAction = onAction,
            value = if (uiState.repsNumber.maxChar) {
                maxCharAlert(4, context)
                uiState.repsNumber.text
            } else uiState.repsNumber.text,
            onValueChange = { onAction(TrackView.Action.OnTextFieldValueChangeRepsNumber(it)) },
            placeHolderText = stringResource(id = R.string.TrackScreen_repsSection_placeHolderText),
            textFieldInWhichSection = TrackViewModel.TrackTextFieldInWhichSection.REPSTEXTFIELD,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Left) }
            )
        )
    }
}

@Composable
fun WeightSection(
    uiState: TrackView.State,
    onAction: (TrackView.Action) -> Unit
) {
    val context = LocalContext.current

    SectionTitle(
        text = stringResource(R.string.TrackScreen_weightSection),
        modifier = Modifier.fillMaxWidth()
    ) {
        CounterAndTextField(
            onAction = onAction,
            value = if (uiState.weightNumber.maxChar) {
                maxCharAlert(3, context)
                uiState.weightNumber.text
            } else uiState.weightNumber.text,
            onValueChange = { onAction(TrackView.Action.OnTextFieldValueChangeWeightNumber(it)) },
            placeHolderText = stringResource(id = R.string.TrackScreen_weightSection_placeHolderText),
            textFieldInWhichSection = TrackViewModel.TrackTextFieldInWhichSection.WEIGHTTEXTFIELD,
            keyboardActions = KeyboardActions(onDone = null)
        )
    }
}

// Title of each section with text and a light gery underline
@Composable
fun SectionTitle(
    text: String,
    modifier: Modifier,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.primaryVariant
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Divider(color = MaterialTheme.colors.primaryVariant, thickness = 2.dp)

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        content()
    }
}

@Composable
fun CounterAndTextField(
    onAction: (TrackView.Action) -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    textFieldInWhichSection: TrackViewModel.TrackTextFieldInWhichSection,
    keyboardActions: KeyboardActions
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 40.dp)
            .fillMaxWidth()
    ) {
        CounterButton(
            onAction = { onAction(TrackView.Action.OnMinusCounterClick(textFieldInWhichSection)) },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_remove_24),
            contentDescription = stringResource(id = R.string.TrackScreen_counterButtonContentDescriptionMinus),
            shape = RoundedCornerShape(topStart = 50.dp, bottomStart = 50.dp),
            modifier = Modifier.weight(1f)
        )

        TrackTextField(
            onAction = onAction,
            value = value,
            onValueChange = onValueChange,
            placeHolderText = placeHolderText,
            keyboardActions = keyboardActions,
            modifier = Modifier.weight(1f)
        )

        CounterButton(
            onAction = { onAction(TrackView.Action.OnPlusCounterClick(textFieldInWhichSection)) },
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_add_24),
            contentDescription = stringResource(id = R.string.TrackScreen_counterButtonContentDescriptionPlus),
            shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CounterButton(
    onAction: () -> Unit,
    shape: Shape,
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier
) {
    Button(
        onClick = onAction,
        shape = shape,
        modifier = modifier.height(56.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

// TimeSection textField with three textFields: hour,minute,second
@Composable
fun TimeTextFieldGroup(
    uiState: TrackView.State,
    onAction: (TrackView.Action) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        TrackTextField(
            onAction = onAction,
            value = if (uiState.hours.maxChar) {
                maxCharAlert(2, context)
                uiState.hours.text
            } else uiState.hours.text,
            onValueChange = { onAction(TrackView.Action.OnTextFieldValueChangeHours(it)) },
            placeHolderText = stringResource(id = R.string.TrackScreen_timeSectionHours_placeHolderText),
            shape = MaterialTheme.shapes.large,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Right) }
            ),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TrackTextField(
            onAction = onAction,
            value = if (uiState.minutes.maxChar) {
                maxCharAlert(2, context)
                uiState.minutes.text
            } else uiState.minutes.text,
            onValueChange = { onAction(TrackView.Action.OnTextFieldValueChangeMinutes(it)) },
            placeHolderText = stringResource(id = R.string.TrackScreen_timeSectionMinutes_placeHolderText),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        TrackTextField(
            onAction = onAction,
            value = if (uiState.seconds.maxChar) {
                maxCharAlert(2, context)
                uiState.seconds.text
            } else uiState.seconds.text,
            onValueChange = { onAction(TrackView.Action.OnTextFieldValueChangeSeconds(it)) },
            placeHolderText = stringResource(id = R.string.TrackScreen_timeSectionSeconds_placeHolderText),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
    }
}

@Composable
fun TrackTextField(
    onAction: (TrackView.Action) -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    shape: Shape? = null,
    textAlign: TextAlign = TextAlign.Center,
    type: TrackTextFieldType = TrackTextFieldType.NUMBER,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.NumberPassword,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions(onDone = null),
    modifier: Modifier = Modifier.height(56.dp)
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = shape ?: RectangleShape
            ),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Transparent
            ),
        textStyle = TextStyle.Default.copy(
            fontSize = 16.sp,
            textAlign = textAlign
        ),
        trailingIcon = when (type) {
            TrackTextFieldType.NUMBER -> null
            TrackTextFieldType.TEXT -> {
                {
                    if (value.isNotEmpty()) {
                        IconButton(onClick = { onAction(TrackView.Action.ClearComment) }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_close_24),
                                contentDescription = stringResource(id = R.string.TrackScreen_commentSectionIconDescription)
                            )
                        }
                    }
                }
            }
        },
        placeholder = {
            Text(
                text = placeHolderText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        singleLine = singleLine,
        shape = shape ?: RectangleShape,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions
    )
}

fun maxCharAlert(
    maxChar: Int,
    context: Context
) {
    Toast.makeText(
        context,
        "Cannot be more than $maxChar Characters",
        Toast.LENGTH_SHORT
    ).show()
}

enum class TrackTextFieldType {
    NUMBER, TEXT
}

