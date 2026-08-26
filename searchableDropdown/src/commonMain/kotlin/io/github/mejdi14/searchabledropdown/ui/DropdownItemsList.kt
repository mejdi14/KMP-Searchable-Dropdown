package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
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
    canReorder: Boolean = false,
    onMove: (from: Int, to: Int) -> Unit = { _, _ -> },
) {
    val listState = rememberLazyListState()

    // Drag-to-reorder state. The item currently being dragged (tracked by identity so it
    // survives the index changes a reorder causes) and its accumulated vertical offset.
    var draggingItem by remember { mutableStateOf<T?>(null) }
    var dragOffset by remember { mutableStateOf(0f) }

    // Keying each row keeps its state (and its drag gesture) attached to the item as the
    // order changes. Keys must be unique and, on Android, Bundle-saveable — hence the
    // caller-provided reorderKey. Only set when reordering.
    val itemKey: ((Int, T) -> Any)? =
        if (canReorder) { _, item -> dropdownConfig.reorderKey(item) } else null

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
    ) {
        searchSettings.searchActionListener.onSearchResults(filteredItems)
        itemsIndexed(filteredItems, key = itemKey) { index, item ->
            val isSelected = item == selectedItem.value
            val isDragging = canReorder && item == draggingItem

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

            val reorderModifier = if (canReorder) {
                Modifier.pointerInput(item) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            draggingItem = item
                            dragOffset = 0f
                        },
                        onDragEnd = {
                            draggingItem = null
                            dragOffset = 0f
                        },
                        onDragCancel = {
                            draggingItem = null
                            dragOffset = 0f
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            dragOffset += dragAmount.y
                            val itemReorderKey = dropdownConfig.reorderKey(item)
                            val info = listState.layoutInfo
                            val dragged = info.visibleItemsInfo.firstOrNull { it.key == itemReorderKey }
                            if (dragged != null) {
                                val draggedCenter = dragged.offset + dragged.size / 2 + dragOffset
                                val target = info.visibleItemsInfo.firstOrNull { candidate ->
                                    candidate.key != itemReorderKey &&
                                        draggedCenter.toInt() in candidate.offset..(candidate.offset + candidate.size)
                                }
                                if (target != null) {
                                    onMove(dragged.index, target.index)
                                    // Keep the dragged row under the finger after the swap.
                                    dragOffset += (dragged.offset - target.offset).toFloat()
                                }
                            }
                        }
                    )
                }
            } else {
                Modifier
            }

            Box(
                Modifier
                    .zIndex(if (isDragging) 1f else 0f)
                    .graphicsLayer {
                        translationY = if (isDragging) dragOffset else 0f
                        if (isDragging) {
                            scaleX = 1.02f
                            scaleY = 1.02f
                        }
                    }
                    .fillMaxWidth()
                    .background(
                        when {
                            isDragging -> Color(0xFFF0F0F0)
                            isSelected -> Color.Gray
                            else -> Color.Transparent
                        }
                    )
                    .then(reorderModifier)
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

