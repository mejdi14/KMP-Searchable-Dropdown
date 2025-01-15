package org.example.dropdown.ui.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.dropdown.data.SearchSettings

@Composable
internal fun <T : Any>SearchArea(searchQuery: MutableState<String>, searchSettings: SearchSettings<T>, ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchIconComposable(searchSettings.searchIcon)
        SearchInputComposable(searchQuery, searchSettings.searchInput)
    }
}
