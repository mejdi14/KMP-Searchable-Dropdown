package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import io.github.mejdi14.searchabledropdown.data.DropdownItemSeparator

@Composable
fun HorizontalDivider(itemSeparator: DropdownItemSeparator) {
    Box(
        modifier = itemSeparator.modifier.height(
            itemSeparator.height
        )
            .background(itemSeparator.color)
    )
}