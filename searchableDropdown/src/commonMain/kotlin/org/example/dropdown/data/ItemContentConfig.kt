package org.example.dropdown.data

import androidx.compose.runtime.Composable

sealed class ItemContentConfig<T : Any> {
    data class Custom<T : Any>(
        val content: @Composable (T, T?) -> Unit,
        val header: @Composable (T, T?) -> Unit = content
    ) : ItemContentConfig<T>()

    data class Default<T : Any>(
        val defaultItem: DefaultDropdownItem<T>
    ) : ItemContentConfig<T>()
}