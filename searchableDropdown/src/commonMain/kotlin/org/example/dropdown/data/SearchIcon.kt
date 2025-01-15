package org.example.dropdown.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.search_icon
import org.jetbrains.compose.resources.DrawableResource

data class SearchIcon (
    val iconDrawable: DrawableResource = Res.drawable.search_icon,
    val iconTintColor: Color = Color.Black,
    val contentDescription: String = "search icon",
    val modifier: Modifier = Modifier,
    val iconSize: Dp = 50.dp
)