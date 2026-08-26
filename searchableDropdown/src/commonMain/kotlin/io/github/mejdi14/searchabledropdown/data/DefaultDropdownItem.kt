package io.github.mejdi14.searchabledropdown.data

import androidx.compose.runtime.Composable
import io.github.mejdi14.searchabledropdown.ui.icon.RoundInitialsIcon
import kotlin.reflect.KProperty1

data class  DefaultDropdownItem<T : Any>(
    val title: KProperty1<T, *>,
    val subtitle: KProperty1<T, *>? = null,
    val withIcon: Boolean = true,
    val icon: @Composable (item: T) -> Unit = { item ->
        RoundInitialsIcon(fullName = title.get(item).toString())
    },
)