package org.example.dropdown.ui.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.enum.DefaultSelectorPosition
import org.example.dropdown.ui.DefaultCheckboxComposable


@Composable
internal fun <T : Any> DefaultMultipleItemComposable(
    item: T,
    defaultDropdownItem: DefaultDropdownItem<T>,
    options: MultipleItemOptions,
    selectedItemsList: MutableList<T>,
) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp, horizontal = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (options.defaultSelectorPosition == DefaultSelectorPosition.START)
            DefaultCheckboxComposable(selectedItemsList, item, options)
        DefaultItemBodyComposable<T>(item, defaultDropdownItem)
        if (options.defaultSelectorPosition == DefaultSelectorPosition.END)
            DefaultCheckboxComposable(selectedItemsList, item, options)
    }
}



