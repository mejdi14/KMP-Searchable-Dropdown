package org.example.dropdown.helper

import androidx.compose.runtime.MutableState
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.data.search.SearchType

internal fun String.matchesQuery(query: String, searchType: SearchType, ignoreCase: Boolean = true): Boolean =
    when (searchType) {
        SearchType.CONTAINS -> this.contains(query, ignoreCase)
        SearchType.EXACT -> this.equals(query, ignoreCase)
        SearchType.STARTS_WITH -> this.startsWith(query, ignoreCase)
        SearchType.ENDS_WITH -> this.endsWith(query, ignoreCase)
        SearchType.REGEX -> query.toRegex(
            if (ignoreCase) setOf(RegexOption.IGNORE_CASE) else emptySet()
        ).containsMatchIn(this)
    }

internal fun <T : Any> filterOperation(
    searchQuery: MutableState<String>,
    items: List<T>,
    searchSettings: SearchSettings<T>
): List<T> = if (searchQuery.value.isEmpty()) {
    items
} else {
    items.filter { item ->
        searchSettings.searchProperties.any { prop ->
            try {
                val value = prop.get(item)?.toString().orEmpty()
                value.matchesQuery(
                    searchQuery.value,
                    searchSettings.searchType,
                    searchSettings.ignoreCase
                )
            } catch (e: Exception) {
                false
            }
        }
    }
}
