package org.example.dropdown.ui.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.enum.DefaultSelectorPosition


@Composable
internal fun <T : Any> DefaultMultipleItemComposable(
    item: T,
    defaultDropdownItem: DefaultDropdownItem<T>,
    options: MultipleItemOptions
) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (options.defaultSelectorPosition == DefaultSelectorPosition.START)
        Checkbox(checked = true, onCheckedChange = {})
        DefaultItemBodyComposable<T>(item, defaultDropdownItem)
        if (options.defaultSelectorPosition == DefaultSelectorPosition.END)
        Checkbox(checked = true, onCheckedChange = {})
    }
}

