package io.github.mejdi14.searchabledropdown.data.base

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

abstract class BaseSeparator {
    abstract val showSeparator: Boolean
    abstract val color: Color
    abstract val height: Dp
    abstract val modifier: Modifier
}