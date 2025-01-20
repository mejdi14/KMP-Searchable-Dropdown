package org.example.dropdown.ui.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.enum.DefaultSelectorPosition


@Composable
internal fun <T : Any> DefaultMultipleItemComposable(
    item: T,
    defaultDropdownItem: DefaultDropdownItem<T>,
    options: MultipleItemOptions,
    selectedItemsList: MutableList<T>
) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (options.defaultSelectorPosition == DefaultSelectorPosition.START)
            Checkbox(checked = selectedItemsList.contains(item), onCheckedChange = { selected ->
                when (selected) {
                    true -> selectedItemsList.add(item)
                    false -> selectedItemsList.remove(item)
                    else -> {}
                }
            })
        DefaultItemBodyComposable<T>(item, defaultDropdownItem)
        if (options.defaultSelectorPosition == DefaultSelectorPosition.END)
            Checkbox(checked = selectedItemsList.contains(item), onCheckedChange = { selected ->
                when (selected) {
                    true -> selectedItemsList.add(item)
                    false -> selectedItemsList.remove(item)
                    else -> {}
                }
            })
    }
}

