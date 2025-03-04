package org.example.dropdown.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.cross_icon
import org.example.dropdown.data.search.SearchIconPosition
import org.example.dropdown.data.search.SearchSettings
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun <T : Any> SearchArea(
    searchQuery: MutableState<String>,
    searchSettings: SearchSettings<T>,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (searchSettings.searchIconPosition == SearchIconPosition.LEFT)
            SearchIconComposable(searchSettings.searchIcon)
        SearchInputComposable(
            searchQuery,
            searchSettings.searchInput,
            searchSettings.searchActionListener
        )
        Icon(
            painter = painterResource(searchSettings.clearSearchIcon.iconDrawable),
            modifier = Modifier.clickable {
                searchQuery.value = ""
            }.size(30.dp),
            contentDescription = "",
            tint = searchSettings.clearSearchIcon.iconTintColor
        )
        if (searchSettings.searchIconPosition == SearchIconPosition.RIGHT)
            SearchIconComposable(searchSettings.searchIcon)
    }
}
