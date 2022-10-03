package com.arielwang.workoutlogger.features.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun ScaffoldWithTopBar(
    @StringRes title: Int,
    @StringRes iconContentDescription: Int,
    onAction: () -> Unit = {},
    modifier: Modifier,
    content: @Composable () -> Unit
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
                backgroundColor = MaterialTheme.colors.onPrimary,
                contentColor = MaterialTheme.colors.secondary,
                elevation = 10.dp
            )
        },

        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.onPrimary)
                    .padding(it),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    )
}

