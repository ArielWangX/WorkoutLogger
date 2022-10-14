package com.arielwang.workoutlogger.features.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    modifier: Modifier,
    buttonText: String,
    onAction: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        onClick = { onAction() }
    ) {
        Text(
            text = buttonText,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}