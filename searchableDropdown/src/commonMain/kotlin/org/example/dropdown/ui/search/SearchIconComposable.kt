package org.example.dropdown.ui.search

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.search_icon
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