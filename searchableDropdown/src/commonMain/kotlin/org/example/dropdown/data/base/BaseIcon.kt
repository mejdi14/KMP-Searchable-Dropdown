package org.example.dropdown.data.base

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.DrawableResource

abstract class BaseIcon {
    abstract val iconDrawable: DrawableResource
    abstract val iconTintColor: Color
    abstract val contentDescription: String
    abstract val modifier: Modifier
    abstract val iconSize: Dp
}