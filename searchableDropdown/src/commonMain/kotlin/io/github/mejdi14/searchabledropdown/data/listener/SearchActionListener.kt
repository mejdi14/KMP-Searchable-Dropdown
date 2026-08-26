package io.github.mejdi14.searchabledropdown.data.listener


interface SearchActionListener<T>  {
    fun onSearchTextWatcher(text: String)
    fun onSearchResults(listMatches: List<T>)
}