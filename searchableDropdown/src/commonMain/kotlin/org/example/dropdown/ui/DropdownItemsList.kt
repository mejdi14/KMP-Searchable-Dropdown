package org.example.dropdown.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.SingleItemContentConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.ui.item.DefaultDropdownItemComposable

@Composable
internal fun <T : Any> DropdownItemsList(
    searchSettings: SearchSettings<T>,
    filteredItems: List<T>,
    selectedItem: MutableState<T?>,
    expanded: MutableState<Boolean>,
    singleItemContentConfig: SingleItemContentConfig<T>,
    dropdownConfig: DropdownConfig<T>
) {
    val listState = rememberLazyListState()
    var containerHeight by remember { mutableStateOf(0f) }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                containerHeight = coordinates.size.height.toFloat()
            },

        ) {
        searchSettings.searchActionListener.onSearchResults(filteredItems)
        itemsIndexed(filteredItems) { index, item ->
            Box(Modifier.fillMaxWidth()
                .background(color = if (item == selectedItem) Color.Gray else Color.Transparent)
                .clickable {
                    if (dropdownConfig.withItemSelection) {
                        selectedItem.value = item
                        dropdownConfig.selectItemActionListener.onItemSelectListener(item)
                        expanded.value = !expanded.value
                    }
                }) {
                when (singleItemContentConfig) {
                    is SingleItemContentConfig.Custom -> singleItemContentConfig.content(
                        item,
                        selectedItem.value
                    )

                    is SingleItemContentConfig.Default -> DefaultDropdownItemComposable(
                        item,
                        singleItemContentConfig.defaultItem
                    )
                }
            }
            if (index != filteredItems.lastIndex && dropdownConfig.itemSeparator.showSeparator) {
                HorizontalDivider(dropdownConfig.itemSeparator)
            }
        }
    }
}


