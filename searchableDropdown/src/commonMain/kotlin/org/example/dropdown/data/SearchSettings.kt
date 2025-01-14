package org.example.dropdown.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.example.dropdown.ui.SearchArea
import org.example.dropdown.ui.SearchSeparator
import kotlin.reflect.KProperty1


data class SearchSettings<T : Any>(
    val searchProperties: List<KProperty1<T, *>> = emptyList(),
    val separator : @Composable () -> Unit = { SearchSeparator() },
    val searchIcon : @Composable () -> Unit = { SearchSeparator() },
    val searchInput: @Composable (MutableState<String>) -> Unit = { query ->
        SearchArea(query)
    },
    val searchType: SearchType = SearchType.CONTAINS,
    val ignoreCase: Boolean = true,
)