package org.example.dropdown.data.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.cross_icon
import org.example.dropdown.data.listener.SearchActionListener
import org.example.dropdown.ui.search.SearchSeparator
import kotlin.reflect.KProperty1


data class SearchSettings<T : Any>(
    val searchEnabled : Boolean = true,
    val searchProperties: List<KProperty1<T, *>> = emptyList(),
    val separator : @Composable () -> Unit = { SearchSeparator() },
    val searchIcon : SearchIcon = SearchIcon(),
    val clearSearchIcon : SearchIcon = SearchIcon(Res.drawable.cross_icon, Color.White),
    val searchInput: SearchInput = SearchInput(),
    val searchType: SearchType = SearchType.CONTAINS,
    val ignoreCase: Boolean = true,
    val searchActionListener: SearchActionListener<T> = object : SearchActionListener<T> {
        override fun onSearchTextWatcher(text: String) {
            // Empty implementation
        }

        override fun onSearchResults(listMatches: List<T>) {
            // Empty implementation
        }
    }
)