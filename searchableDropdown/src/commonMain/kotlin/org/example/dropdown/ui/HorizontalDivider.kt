package org.example.dropdown.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import org.example.dropdown.data.DropdownItemSeparator

@Composable
fun HorizontalDivider(itemSeparator: DropdownItemSeparator) {
    Box(
        modifier = itemSeparator.modifier.height(
            itemSeparator.height
        )
            .background(itemSeparator.color)
    )
}