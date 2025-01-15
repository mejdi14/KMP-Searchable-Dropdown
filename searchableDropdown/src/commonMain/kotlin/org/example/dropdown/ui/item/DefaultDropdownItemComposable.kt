package org.example.dropdown.ui.item

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import org.example.dropdown.data.DefaultDropdownItem

@Composable
internal fun <T : Any>DefaultDropdownItemComposable(
    item: T,
    defaultDropdownItem: DefaultDropdownItem<T>?
) {
    Row(verticalAlignment = Alignment.CenterVertically){
        if (defaultDropdownItem?.withIcon == true) {
            defaultDropdownItem.icon(item)
        }
        defaultDropdownItem?.title?.let {
            Text(text = it.get(item).toString())
        }

    }
}