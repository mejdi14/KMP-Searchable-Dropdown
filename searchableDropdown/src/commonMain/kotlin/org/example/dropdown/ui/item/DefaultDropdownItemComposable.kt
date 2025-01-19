package org.example.dropdown.ui.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.dropdown.data.DefaultDropdownItem

@Composable
internal fun <T : Any> DefaultDropdownItemComposable(
    item: T,
    defaultDropdownItem: DefaultDropdownItem<T>
) {
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        if (defaultDropdownItem.withIcon == true) {
            defaultDropdownItem.icon(item)
            Spacer(Modifier.width(10.dp))
        }
        Text(text = defaultDropdownItem.title.get(item).toString(), modifier = Modifier.weight(1f))
        Text(text = defaultDropdownItem.subtitle?.get(item).toString(), color = Color.Gray, fontSize = 12.sp)
    }
}