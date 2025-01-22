package org.example.dropdown.data.selection

import androidx.compose.runtime.Composable
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.listener.MultipleRemoveItemListener
import org.example.dropdown.data.listener.MultipleSelectActionListener
import org.example.dropdown.ui.item.DefaultItemHeaderComposable
import org.example.dropdown.ui.item.MultipleItemOptions

sealed class MultipleItemContentConfig<T : Any>  : ItemContentConfig<T>{
    data class Custom<T : Any>(
        val content: @Composable (T, Boolean, MultipleSelectActionListener<T>) -> Unit,
        val header: @Composable (T, T?, MultipleRemoveItemListener<T>) -> Unit,
        val options: MultipleItemOptions = MultipleItemOptions(),
    ) : MultipleItemContentConfig<T>()


    data class Default<T : Any>(
        val defaultItemCustomization: DefaultDropdownItem<T>,
        val options: MultipleItemOptions = MultipleItemOptions(),
    ) : MultipleItemContentConfig<T>()
}