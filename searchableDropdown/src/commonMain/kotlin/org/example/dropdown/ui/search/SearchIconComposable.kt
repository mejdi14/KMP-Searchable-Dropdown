package org.example.dropdown.ui.search

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import org.example.dropdown.data.SearchIcon
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SearchIconComposable(searchIcon: SearchIcon) {
    Icon(
        painterResource(searchIcon.iconDrawable), searchIcon.contentDescription,
        modifier = searchIcon.modifier,
        tint = searchIcon.iconTintColor,
        )
}