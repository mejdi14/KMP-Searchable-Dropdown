package org.example.dropdown.data.selection

import androidx.compose.runtime.Composable
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.listener.SelectActionListener
import org.example.dropdown.ui.icon.DeleteSelectionIcon
import org.example.dropdown.ui.icon.RoundInitialsIcon
import kotlin.reflect.KProperty1

sealed class MultipleItemContentConfig<T : Any> {
    data class Custom<T : Any>(
        val content: @Composable (T, T?, SelectActionListener) -> Unit,
        val header: @Composable (T, T?, SelectActionListener) -> Unit = content
    ) : SingleItemContentConfig<T>()

    data class Default<T : Any>(
        val defaultItem: DefaultDropdownItem<T>
    ) : SingleItemContentConfig<T>()
}