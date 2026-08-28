package io.github.mejdi14.searchabledropdown.data.selection

import androidx.compose.runtime.Composable
import io.github.mejdi14.searchabledropdown.data.DefaultDropdownItem

sealed class SingleItemContentConfig<T : Any>  : ItemContentConfig<T>{
    data class Custom<T : Any>(
        val content: @Composable (T, T?) -> Unit,
        val header: @Composable (T, T?) -> Unit = content
    ) : SingleItemContentConfig<T>()

    data class Default<T : Any>(
        val defaultItem: DefaultDropdownItem<T>
    ) : SingleItemContentConfig<T>()
}
