package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.ItemContentConfig
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.searchabledropdown.data.selection.SingleItemContentConfig
import io.github.mejdi14.searchabledropdown.helper.multipleSelectActionListener
import io.github.mejdi14.searchabledropdown.ui.item.CustomMultipleItemComposable
import io.github.mejdi14.searchabledropdown.ui.item.DefaultMultipleItemComposable
import io.github.mejdi14.searchabledropdown.ui.item.DefaultSingleItemComposable

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

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
    ) {
        searchSettings.searchActionListener.onSearchResults(filteredItems)
        itemsIndexed(filteredItems) { index, item ->
            val isSelected = item == selectedItem.value
            val clickModifier =
                if (dropdownConfig.withItemSelection && itemContentConfig is SingleItemContentConfig) {
                    Modifier.clickable {
                        selectedItem.value = item
                        dropdownConfig.dropdownActionListener.onItemSelect(item)
                        expanded.value = !expanded.value
                    }
                } else {
                    Modifier
                }

            Box(
                Modifier.fillMaxWidth()
                    .background(if (isSelected) Color.Gray else Color.Transparent)
                    .then(clickModifier)
            ) {
                when (itemContentConfig) {
                    is SingleItemContentConfig ->
                        SingleItemRow(item, itemContentConfig)

                    is MultipleItemContentConfig ->
                        MultipleItemRow(item, itemContentConfig, selectedItemsList)
                }
            }
            if (index != filteredItems.lastIndex && dropdownConfig.itemSeparator.showSeparator) {
                HorizontalDivider(dropdownConfig.itemSeparator)
            }
        }
    }
}

@Composable
private fun <T : Any> SingleItemRow(
    item: T,
    config: SingleItemContentConfig<T>,
) {
    when (config) {
        is SingleItemContentConfig.Custom -> config.content(item, null)
        is SingleItemContentConfig.Default -> DefaultSingleItemComposable(item, config.defaultItem)
    }
}

@Composable
private fun <T : Any> MultipleItemRow(
    item: T,
    config: MultipleItemContentConfig<T>,
    selectedItemsList: SnapshotStateList<T>,
) {
    when (config) {
        is MultipleItemContentConfig.Custom -> {
            val listener = remember(selectedItemsList, config.options.selectionMaxCount) {
                multipleSelectActionListener(selectedItemsList, config.options.selectionMaxCount)
            }
            val bodyContent = @Composable {
                config.content(item, selectedItemsList.contains(item), listener)
            }
            if (config.options.useDefaultSelector) {
                CustomMultipleItemComposable(item, config.options, selectedItemsList, bodyContent)
            } else {
                bodyContent()
            }
        }

        is MultipleItemContentConfig.Default -> DefaultMultipleItemComposable(
            item,
            config.defaultItemCustomization,
            config.options,
            selectedItemsList,
        )
    }
}

