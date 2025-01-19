package org.example.dropdown.data.search

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.search_icon
import org.example.dropdown.data.base.BaseIcon
import org.jetbrains.compose.resources.DrawableResource

data class SearchIcon(
    override val iconDrawable: DrawableResource = Res.drawable.search_icon,
    override val iconTintColor: Color = Color.Black,
    override val contentDescription: String = "search icon",
    override val modifier: Modifier = Modifier,
    override val iconSize: Dp = 50.dp
) : BaseIcon()