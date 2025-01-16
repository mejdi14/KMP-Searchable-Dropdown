package org.example.dropdown.ui.search

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.example.dropdown.data.search.SearchInput

@Composable
internal fun SearchInputComposable(searchQuery: MutableState<String>, searchInput: SearchInput) {
    OutlinedTextField(
        modifier = searchInput.modifier,
        value = searchQuery.value,
        onValueChange = { searchQuery.value = it },
        placeholder = searchInput.placeholder,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = searchInput.backgroundColor,
            focusedIndicatorColor = searchInput.focusedIndicatorColor,
            unfocusedIndicatorColor = searchInput.unfocusedIndicatorColor,
            disabledIndicatorColor = searchInput.disabledIndicatorColor
        ),
        keyboardOptions = searchInput.keyboardOptions,
    )
}