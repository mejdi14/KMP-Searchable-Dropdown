package org.example.dropdown.data.selection

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


data class CheckboxParams(
    val checkedColor: Color = Color.Green,
    val uncheckedColor: Color = Color.Gray,
    val checkmarkColor: Color = Color.Black,
    val size: Dp = 24.dp
)
