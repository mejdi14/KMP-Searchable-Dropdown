package org.example.dropdown.ui.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun SearchArea(searchQuery: MutableState<String>, ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchIconComposable()
        SearchInputComposable(searchQuery)
    }
}
