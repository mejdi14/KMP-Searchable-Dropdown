package org.example.dropdown.data

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.dropdown.ui.search.SearchSeparator

data class SearchInput (
    val modifier: Modifier = Modifier.fillMaxWidth().height(56.dp),
    val placeholder: @Composable () -> Unit = { Text("Searching...") },
    val backgroundColor: Color = Color.Transparent,
    val focusedIndicatorColor: Color = Color.Transparent,
    val unfocusedIndicatorColor: Color = Color.Transparent,
    val disabledIndicatorColor: Color = Color.Transparent,
    val keyboardOptions: KeyboardOptions  = KeyboardOptions(
        imeAction = ImeAction.Search,
    ),

)