package org.example.dropdown.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.expand_less
import org.example.dropdown.data.base.BaseIcon
import org.jetbrains.compose.resources.DrawableResource

data class ToggleIcon(
    override val iconDrawable: DrawableResource = Res.drawable.expand_less,
    override val iconTintColor: Color = Color.Black,
    override val contentDescription: String = "toggle icon",
    override val modifier: Modifier = Modifier,
    override val iconSize: Dp = 20.dp,
    val animateRotation: Boolean = true,
    val animateScale: Boolean = false,
): BaseIcon()