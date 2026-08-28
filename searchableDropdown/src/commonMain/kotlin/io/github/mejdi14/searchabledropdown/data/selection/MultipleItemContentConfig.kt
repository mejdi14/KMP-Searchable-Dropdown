package io.github.mejdi14.searchabledropdown.data.selection

import androidx.compose.runtime.Composable
import io.github.mejdi14.searchabledropdown.data.DefaultDropdownItem
import io.github.mejdi14.searchabledropdown.data.listener.MultipleRemoveItemListener
import io.github.mejdi14.searchabledropdown.data.listener.MultipleSelectActionListener
import io.github.mejdi14.searchabledropdown.ui.item.DefaultItemHeaderComposable
import io.github.mejdi14.searchabledropdown.ui.item.MultipleItemOptions

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
