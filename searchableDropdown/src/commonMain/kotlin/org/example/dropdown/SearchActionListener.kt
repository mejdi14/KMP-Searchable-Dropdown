package org.example.dropdown


interface SearchActionListener<T>  {
    fun onSearchTextWatcher(text: String)
    fun onSearchResults(listMatches: List<T>)
}