package com.arielwang.workoutlogger.features.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arielwang.workoutlogger.R

@Composable
fun WorkoutLoggerTextField(
    onAction: () -> Unit = {},
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String = "",
    shape: Shape? = null,
    textAlign: TextAlign = TextAlign.Center,
    type: TextFieldType = TextFieldType.NUMBER,
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
                textColor = MaterialTheme.colors.secondary,
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
            TextFieldType.NUMBER -> null
            TextFieldType.TEXT -> {
                {
                    if (value.isNotEmpty()) {
                        IconButton(onClick = onAction) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_close_24),
                                contentDescription = stringResource(id = R.string.TrackScreen_commentSectionIconDescription),
                                tint = MaterialTheme.colors.primaryVariant
                            )
                        }
                    }
                }
            }
        },
        placeholder = {
            Text(
                text = placeHolderText,
                color = MaterialTheme.colors.secondaryVariant,
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

enum class TextFieldType {
    NUMBER, TEXT
}
