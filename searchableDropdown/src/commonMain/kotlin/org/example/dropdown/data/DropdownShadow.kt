package org.example.dropdown.data

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DropdownShadow(
    val showShadow: Boolean = true,
    val elevation: Dp = 2.dp,
    val shape: Shape
)