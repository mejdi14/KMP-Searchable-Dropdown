package org.example.dropdown.data.base

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.search_icon
import org.jetbrains.compose.resources.DrawableResource

internal abstract class BaseIcon {
    abstract val iconDrawable: DrawableResource
    abstract val iconTintColor: Color
    abstract val contentDescription: String
    abstract val modifier: Modifier
    abstract val iconSize: Dp
}