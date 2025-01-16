package org.example.dropdown.data.selection

import androidx.compose.runtime.Composable
import org.example.dropdown.ui.icon.DeleteSelectionIcon
import org.example.dropdown.ui.icon.RoundInitialsIcon
import kotlin.reflect.KProperty1

class MultipleSelectionConfig<T : Any>(
    val text: KProperty1<T, *>,
    val subtitle: KProperty1<T, *>? = null,
    val withIcon: Boolean = true,
    val icon: @Composable (item: T) -> Unit = { item ->
        RoundInitialsIcon(fullName = text.get(item).toString())
    },
    val deleteIcon: @Composable () -> Unit = {
        DeleteSelectionIcon()
    },
    val maxItemsCount: Int? = null,
)