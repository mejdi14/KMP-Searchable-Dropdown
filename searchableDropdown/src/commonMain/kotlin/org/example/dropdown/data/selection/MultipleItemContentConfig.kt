package org.example.dropdown.data.selection

import androidx.compose.runtime.Composable
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.listener.MultipleRemoveItemListener
import org.example.dropdown.data.listener.MultipleSelectActionListener
import org.example.dropdown.data.listener.SelectActionListener
import org.example.dropdown.ui.icon.DeleteSelectionIcon
import org.example.dropdown.ui.icon.RoundInitialsIcon
import org.example.dropdown.ui.item.MultipleItemOptions
import kotlin.reflect.KProperty1

sealed class MultipleItemContentConfig<T : Any>  : ItemContentConfig<T>{
    data class Custom<T : Any>(
        val content: @Composable (T, T?, MultipleSelectActionListener<T>) -> Unit,
        val header: @Composable (T, T?, MultipleRemoveItemListener<T>) -> Unit,
        val options: MultipleItemOptions = MultipleItemOptions(),
    ) : MultipleItemContentConfig<T>()

    data class Default<T : Any>(
        val defaultItem: DefaultDropdownItem<T>
    ) : MultipleItemContentConfig<T>()
}