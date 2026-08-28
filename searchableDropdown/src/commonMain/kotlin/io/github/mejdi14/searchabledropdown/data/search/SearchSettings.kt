package io.github.mejdi14.searchabledropdown.data.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kmp_searchable_dropdown.searchabledropdown.generated.resources.Res
import kmp_searchable_dropdown.searchabledropdown.generated.resources.cross_icon
import io.github.mejdi14.searchabledropdown.data.listener.SearchActionListener
import io.github.mejdi14.searchabledropdown.ui.search.SearchSeparator
import kotlin.reflect.KProperty1

data class SearchSettings<T : Any>(
    val searchEnabled : Boolean = true,
    val searchProperties: List<KProperty1<T, *>> = emptyList(),
    val separator : @Composable () -> Unit = { SearchSeparator() },
    val searchIcon : SearchIcon = SearchIcon(),
    val clearSearchIcon : SearchIcon = SearchIcon(Res.drawable.cross_icon, Color(0xFF9E9E9E)),
    val searchInput: SearchInput = SearchInput(),
    val searchType: SearchType = SearchType.CONTAINS,
    val ignoreCase: Boolean = true,

    val searchLocation: SearchLocation = SearchLocation.POPUP,
    val searchActionListener: SearchActionListener<T> = object : SearchActionListener<T> {
        override fun onSearchTextWatcher(text: String) {

        }

        override fun onSearchResults(listMatches: List<T>) {

        }
    }
)
