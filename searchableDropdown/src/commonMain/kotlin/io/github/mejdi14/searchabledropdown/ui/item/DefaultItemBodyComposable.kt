package io.github.mejdi14.searchabledropdown.ui.item

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mejdi14.searchabledropdown.data.DefaultDropdownItem

@Composable
internal fun <T : Any> RowScope.DefaultItemBodyComposable(
    item: T,
    defaultDropdownItem: DefaultDropdownItem<T>,
) {
    if (defaultDropdownItem.withIcon) {
        defaultDropdownItem.icon(item)
        Spacer(Modifier.width(10.dp))
    }
    Text(text = defaultDropdownItem.title.get(item).toString(), modifier = Modifier.weight(1f))
    val subtitle = defaultDropdownItem.subtitle?.get(item)?.toString()
    if (subtitle != null) {
        Text(
            text = subtitle,
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}