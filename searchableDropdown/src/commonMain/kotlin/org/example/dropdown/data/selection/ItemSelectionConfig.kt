package org.example.dropdown.data.selection

import androidx.compose.runtime.Composable
import org.example.dropdown.data.DefaultDropdownItem


sealed class ItemSelectionConfig<T : Any> {
    data class SingleSelection<T : Any>(
        val content: @Composable (T) -> Unit
    ) : ItemSelectionConfig<T>()

    data class MultipleSelection<T : Any>(
        val defaultItem: DefaultDropdownItem<T>
    ) : ItemSelectionConfig<T>()
}