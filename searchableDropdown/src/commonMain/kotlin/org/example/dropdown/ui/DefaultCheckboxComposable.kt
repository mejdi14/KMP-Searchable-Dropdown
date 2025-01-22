package org.example.dropdown.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.dropdown.ui.item.MultipleItemOptions

@Composable
internal fun <T : Any> DefaultCheckboxComposable(
    selectedItemsList: MutableList<T>,
    item: T,
    options: MultipleItemOptions
) {
    Checkbox(
        checked = selectedItemsList.contains(item), onCheckedChange = { selected ->
            when (selected) {
                true -> selectedItemsList.add(item)
                false -> selectedItemsList.remove(item)
                else -> {}
            }
        },
        colors = CheckboxDefaults.colors(
            checkedColor = options.defaultCheckboxParams.checkedColor, // Use params
            uncheckedColor = options.defaultCheckboxParams.uncheckedColor,
            checkmarkColor = options.defaultCheckboxParams.checkmarkColor
        ),
        modifier = Modifier.size(options.defaultCheckboxParams.size)
    )
}