package org.example.project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun DropdownItem(skill: String) {
    Text(
        text = skill,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle item click */ }
            .padding(16.dp),
        color = Color.Black
    )
}