package com.arielwang.workoutlogger.features.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            color = MaterialTheme.colors.secondary
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Divider(color = MaterialTheme.colors.secondaryVariant, thickness = 2.dp)

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        content()
    }
}