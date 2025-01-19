package org.example.dropdown.data.listener


interface SearchActionListener<T>  {
    fun onSearchTextWatcher(text: String)
    fun onSearchResults(listMatches: List<T>)
}