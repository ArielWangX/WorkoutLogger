package com.arielwang.workoutlogger.features.exercise.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arielwang.workoutlogger.R
import com.arielwang.workoutlogger.features.component.ScaffoldWithTopBar
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.toPaddingValues

@Composable
fun ExerciseScreen(
    uiState: ExerciseView.State = ExerciseView.State(),
    onAction: (ExerciseView.Action) -> Unit
) {
    ScaffoldWithTopBar(
        title = R.string.ExerciseScreen_topbarTitle,
        iconContentDescription = R.string.ExerciseScreen_topbarIconButtonContentDescription,
        onAction = { onAction(ExerciseView.Action.GoBackToPreviousPage)},
        modifier = Modifier.systemBarsPadding()
    ) {
        ExerciseContentConstrainLayout(uiState = uiState, onAction = onAction)
    }

}

@Composable
fun ExerciseContentConstrainLayout(
    uiState: ExerciseView.State,
    onAction: (ExerciseView.Action) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val (button, cardListSection) = createRefs()

        ExerciseCardList(
            uiState = uiState,
            onAction = onAction,
            cardListConstrainLayout = Modifier.constrainAs(cardListSection) {
                top.linkTo(parent.top)
                bottom.linkTo(button.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        ExerciseScreenButton(
            onAction = onAction,
            buttonConstraintLayout = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun ExerciseCardList(
    uiState: ExerciseView.State,
    onAction: (ExerciseView.Action) -> Unit,
    cardListConstrainLayout: Modifier
) {
    LazyColumn(
        modifier = cardListConstrainLayout
    ) {
        item {Spacer(modifier = Modifier.height(30.dp))}

        uiState.cardList.forEach {
            item { ExerciseCard(text = it.text, isSelected = it.isSelected, onAction = onAction) }
        }

        item {Spacer(modifier = Modifier.height(20.dp))}
    }
}

@Composable
fun ExerciseCard(
    text: String,
    isSelected: Boolean,
    onAction: (ExerciseView.Action) -> Unit
) {
    Card(
        border = BorderStroke(
            2.dp,
            if (isSelected)
                MaterialTheme.colors.primaryVariant
            else
                MaterialTheme.colors.onSecondary
        ),
        backgroundColor = MaterialTheme.colors.onPrimary,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .clickable { onAction(ExerciseView.Action.OnCardClicked(text)) }
        ) {
            Text(
                text = text,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = MaterialTheme.colors.primaryVariant,
                    contentDescription = null
                )
            }
        }
    }
}


@Composable
fun ExerciseScreenButton(
    buttonConstraintLayout: Modifier,
    onAction: (ExerciseView.Action) -> Unit = {}
) {
    Button(
        modifier = buttonConstraintLayout
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        onClick = { onAction(ExerciseView.Action.GoToNextPage) }
    ) {
        Text(
            text = stringResource(id = R.string.ExerciseScreen_buttonContent),
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}
