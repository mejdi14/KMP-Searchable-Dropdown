package org.example.dropdown.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.example.dropdown.ui.search.SearchArea
import org.example.dropdown.ui.search.SearchSeparator
import kotlin.reflect.KProperty1


data class SearchSettings<T : Any>(
    val searchProperties: List<KProperty1<T, *>> = emptyList(),
    val separator : @Composable () -> Unit = { SearchSeparator() },
    val searchIcon : SearchIcon = SearchIcon(),
    val searchInput: SearchInput = SearchInput(),
    val searchType: SearchType = SearchType.CONTAINS,
    val ignoreCase: Boolean = true,
)