package org.example.dropdown.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EmptySearchContent() {
    Text(
        text = "No results found",
        color = Color.Gray,
        modifier = Modifier.padding(16.dp)
    )
}