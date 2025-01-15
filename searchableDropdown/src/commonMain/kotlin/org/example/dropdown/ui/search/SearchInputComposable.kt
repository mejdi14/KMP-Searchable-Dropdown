package org.example.dropdown.ui.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun SearchInputComposable(searchQuery: MutableState<String>) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        value = searchQuery.value,
        onValueChange = { searchQuery.value = it },
        placeholder = { Text("Searching...") },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}