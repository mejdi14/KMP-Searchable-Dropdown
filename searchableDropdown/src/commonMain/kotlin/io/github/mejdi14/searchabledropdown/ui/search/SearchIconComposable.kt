package io.github.mejdi14.searchabledropdown.ui.search

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SearchIconComposable(searchIcon: SearchIcon) {
    Icon(
        painterResource(searchIcon.iconDrawable), searchIcon.contentDescription,
        modifier = searchIcon.modifier,
        tint = searchIcon.iconTintColor,
        )
}