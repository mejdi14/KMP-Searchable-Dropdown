package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.mejdi14.searchabledropdown.helper.selectWithLimit
import io.github.mejdi14.searchabledropdown.ui.item.MultipleItemOptions

@Composable
internal fun <T : Any> DefaultCheckboxComposable(
    selectedItemsList: MutableList<T>,
    item: T,
    options: MultipleItemOptions
) {
    Checkbox(
        checked = selectedItemsList.contains(item), onCheckedChange = { selected ->
            if (selected) {
                selectedItemsList.selectWithLimit(item, options.selectionMaxCount)
            } else {
                selectedItemsList.remove(item)
            }
        },
        colors = CheckboxDefaults.colors(
            checkedColor = options.defaultCheckboxParams.checkedColor,
            uncheckedColor = options.defaultCheckboxParams.uncheckedColor,
            checkmarkColor = options.defaultCheckboxParams.checkmarkColor
        ),
        modifier = Modifier.size(options.defaultCheckboxParams.size)
    )
}
