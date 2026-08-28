package io.github.mejdi14.searchabledropdown.ui.search

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.mejdi14.searchabledropdown.data.search.SearchIcon
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SearchIconComposable(searchIcon: SearchIcon) {
    Icon(
        painter = painterResource(searchIcon.iconDrawable),
        contentDescription = searchIcon.contentDescription,
        modifier = searchIcon.modifier.then(Modifier.size(searchIcon.iconSize)),
        tint = searchIcon.iconTintColor,
    )
}
