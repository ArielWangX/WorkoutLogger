package com.arielwang.workoutlogger.features.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun WorkoutLoggerScaffold(
    @StringRes title: Int,
    @StringRes iconContentDescription: Int,
    onAction: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = title))
                },
                navigationIcon = {
                    IconButton(onClick = { onAction() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(id = iconContentDescription))
                    }
                },
                actions = actions,
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.onSecondary,
                elevation = 8.dp
            )
        },
        content = content
    )
}

