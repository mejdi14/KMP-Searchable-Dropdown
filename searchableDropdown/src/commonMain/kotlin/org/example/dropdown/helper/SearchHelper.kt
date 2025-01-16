package org.example.dropdown.helper

import org.example.dropdown.data.search.SearchType

fun String.matchesQuery(query: String, searchType: SearchType, ignoreCase: Boolean = true): Boolean {
    return when (searchType) {
        SearchType.CONTAINS -> this.contains(query, ignoreCase)
        SearchType.EXACT -> this.equals(query, ignoreCase)
        SearchType.STARTS_WITH -> this.startsWith(query, ignoreCase)
        SearchType.ENDS_WITH -> this.endsWith(query, ignoreCase)
        SearchType.REGEX -> query.toRegex(
            if (ignoreCase) setOf(RegexOption.IGNORE_CASE) else emptySet()
        ).containsMatchIn(this)
    }
}
