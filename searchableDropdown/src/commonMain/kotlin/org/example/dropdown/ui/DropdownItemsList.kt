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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.listener.MultipleSelectActionListener
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.data.selection.ItemContentConfig
import org.example.dropdown.data.selection.MultipleItemContentConfig
import org.example.dropdown.data.selection.SingleItemContentConfig
import org.example.dropdown.ui.item.CustomMultipleItemComposable
import org.example.dropdown.ui.item.DefaultItemBodyComposable
import org.example.dropdown.ui.item.DefaultMultipleItemComposable
import org.example.dropdown.ui.item.DefaultSingleItemComposable

@Composable
internal fun <T : Any> DropdownItemsList(
    searchSettings: SearchSettings<T>,
    filteredItems: List<T>,
    selectedItem: MutableState<T?>,
    expanded: MutableState<Boolean>,
    itemContentConfig: ItemContentConfig<T>,
    dropdownConfig: DropdownConfig<T>,
    selectedItemsList: SnapshotStateList<T>,
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
                .then(
                    if (dropdownConfig.withItemSelection && itemContentConfig is SingleItemContentConfig) {
                        Modifier.clickable {
                            selectedItem.value = item
                            dropdownConfig.selectItemActionListener.onItemSelectListener(item)
                            expanded.value = !expanded.value
                        }
                    } else {
                        Modifier
                    }
                )
                )
            {

                when (itemContentConfig) {
                    is SingleItemContentConfig -> {
                        when (itemContentConfig) {
                            is SingleItemContentConfig.Custom ->
                                itemContentConfig.content(
                                item,
                                null
                            )

                            is SingleItemContentConfig.Default -> DefaultSingleItemComposable(
                                item,
                                itemContentConfig.defaultItem
                            )
                        }
                    }
                    is MultipleItemContentConfig -> {
                        when (itemContentConfig) {
                            is MultipleItemContentConfig.Custom ->
                                CustomMultipleItemComposable(
                                    item,
                                    itemContentConfig.options,
                                    selectedItemsList,
                                bodyContent = {
                                    itemContentConfig.content(
                                        item,
                                        null, object : MultipleSelectActionListener<T>{
                                            override fun onSelect(item: T) {
                                                selectedItemsList.add(item)
                                            }

                                            override fun onDeselect(item: T) {
                                                selectedItemsList.remove(item)
                                            }

                                            override fun isSelected(item: T): Boolean {
                                                return selectedItemsList.contains(item)
                                            }


                                        }
                                    )
                                })

                            is MultipleItemContentConfig.Default -> DefaultMultipleItemComposable(
                                item,
                                itemContentConfig.defaultItemCustomization,
                                itemContentConfig.options,
                                selectedItemsList,

                            )
                        }
                    }
                }
            }
            if (index != filteredItems.lastIndex && dropdownConfig.itemSeparator.showSeparator) {
                HorizontalDivider(dropdownConfig.itemSeparator)
            }
        }
    }
}


