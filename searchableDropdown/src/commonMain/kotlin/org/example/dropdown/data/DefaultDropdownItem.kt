package org.example.dropdown.data

import androidx.compose.runtime.Composable
import org.example.dropdown.ui.icon.RoundInitialsIcon
import kotlin.reflect.KProperty1

data class DefaultDropdownItem<T : Any>(
    val title: KProperty1<T, *>,
    val subtitle: KProperty1<T, *>? = null,
    val withIcon: Boolean = true,
    val icon: @Composable (item: T) -> Unit = { item ->
        RoundInitialsIcon(fullName = title.get(item).toString())
    },
)