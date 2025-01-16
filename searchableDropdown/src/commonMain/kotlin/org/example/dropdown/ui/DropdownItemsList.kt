package org.example.dropdown.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.ui.item.DefaultDropdownItemComposable

@Composable
internal fun <T : Any> DropdownItemsList(
    searchSettings: SearchSettings<T>,
    filteredItems: List<T>,
    selectedItem: MutableState<T?>,
    expanded: MutableState<Boolean>,
    itemContentConfig: ItemContentConfig<T>,
    items: List<T>,
    dropdownConfig: DropdownConfig
) {
    LazyColumn(
        Modifier.fillMaxWidth(),
    ) {
        searchSettings.searchActionListener.onSearchResults(filteredItems)
        itemsIndexed(filteredItems) { index, item ->
            Box(Modifier.fillMaxWidth().background(Color.Red)
                .clickable {
                    selectedItem.value = item
                    expanded.value = !expanded.value
                }) {
                when (itemContentConfig) {
                    is ItemContentConfig.Custom -> itemContentConfig.content
                    is ItemContentConfig.Default -> DefaultDropdownItemComposable(
                        item,
                        itemContentConfig.defaultItem
                    )
                }
            }
            if (index != items.lastIndex && dropdownConfig.itemSeparator.showSeparator) {
                HorizontalDivider(dropdownConfig.itemSeparator)
            }
        }
    }
}
