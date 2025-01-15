package org.example.dropdown.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.SearchInput

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