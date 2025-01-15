package org.example.dropdown.data

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.dropdown.ui.search.SearchSeparator

data class SearchInput (
    val modifier: Modifier = Modifier.fillMaxWidth().height(56.dp),
    val placeholder: @Composable () -> Unit = { Text("Searching...") },
)