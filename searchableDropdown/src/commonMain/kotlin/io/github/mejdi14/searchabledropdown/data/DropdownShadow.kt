package io.github.mejdi14.searchabledropdown.data

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DropdownShadow(
    val showShadow: Boolean = true,
    val elevation: Dp = 2.dp,
    val shape: Shape
)
