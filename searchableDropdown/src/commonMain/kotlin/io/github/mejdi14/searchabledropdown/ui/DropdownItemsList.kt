package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.awaitLongPressOrCancellation
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
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

/** Maximum edge auto-scroll speed while reordering, in pixels per frame. */
private const val MAX_AUTO_SCROLL = 20f

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

    // Drag-to-reorder state. The gesture lives on the list container (not the rows), so it
    // survives rows being disposed as the list auto-scrolls. We track the dragged item, its
    // current index, the finger position in list coordinates, and the edge auto-scroll speed.
    val draggingItem = remember { mutableStateOf<T?>(null) }
    val draggingIndex = remember { mutableStateOf(-1) }
    val pointerY = remember { mutableStateOf(0f) }
    val autoScrollSpeed = remember { mutableStateOf(0f) }

    // Keying each row keeps its state attached to the item as the order changes. Keys must be
    // unique and, on Android, Bundle-saveable — hence the caller-provided reorderKey.
    val itemKey: ((Int, T) -> Any)? =
        if (canReorder) { _, item -> dropdownConfig.reorderKey(item) } else null

    fun resetDrag() {
        draggingItem.value = null
        draggingIndex.value = -1
        autoScrollSpeed.value = 0f
    }

    // Move the dragged item to whichever row the finger currently sits over, but only once the
    // finger has crossed that row's midpoint. The midpoint check adds hysteresis so the swap
    // doesn't flip back and forth while the finger hovers near a boundary. Suspend because it
    // may need to restore the scroll position (see below); it is only called from the loop.
    suspend fun checkSwap() {
        val from = draggingIndex.value
        if (from < 0) return
        val y = pointerY.value
        val target = listState.layoutInfo.visibleItemsInfo.firstOrNull {
            it.index != from && y.toInt() in it.offset..(it.offset + it.size)
        } ?: return
        val targetMid = target.offset + target.size / 2f
        val crossedMidpoint = if (target.index < from) y < targetMid else y > targetMid
        if (!crossedMidpoint) return

        val to = target.index
        // Capture the scroll anchor so we can restore it if the move changes the first visible
        // row — otherwise LazyColumn re-anchors and the viewport visibly jumps (the "the top
        // item jitters as it makes room" glitch when the dragged row reaches the top).
        val firstIndex = listState.firstVisibleItemIndex
        val firstOffset = listState.firstVisibleItemScrollOffset
        onMove(from, to)
        draggingIndex.value = to
        if (from == firstIndex || to == firstIndex) {
            listState.scrollToItem(firstIndex, firstOffset)
        }
    }

    // Set the auto-scroll speed based on how close the finger is to a viewport edge.
    fun updateAutoScrollSpeed() {
        if (draggingIndex.value < 0) {
            autoScrollSpeed.value = 0f
            return
        }
        val info = listState.layoutInfo
        val edge = (info.visibleItemsInfo.firstOrNull()?.size ?: 0).toFloat().coerceAtLeast(1f)
        val topZone = info.viewportStartOffset + edge
        val bottomZone = info.viewportEndOffset - edge
        val y = pointerY.value
        autoScrollSpeed.value = when {
            y < topZone -> -((topZone - y) / edge).coerceIn(0f, 1f) * MAX_AUTO_SCROLL
            y > bottomZone -> ((y - bottomZone) / edge).coerceIn(0f, 1f) * MAX_AUTO_SCROLL
            else -> 0f
        }
    }

    // A single per-frame loop drives all reordering while a drag is active. Running once per
    // frame (after layout has settled) keeps swaps in sync with the list — doing it here rather
    // than inside onDrag avoids double-swapping and the stale-layout glitch near the edges.
    LaunchedEffect(draggingItem.value) {
        if (draggingItem.value == null) return@LaunchedEffect
        while (true) {
            withFrameNanos { }
            checkSwap()
            val speed = autoScrollSpeed.value
            if (speed != 0f) {
                listState.scrollBy(speed)
            }
        }
    }

    val reorderModifier = if (canReorder) {
        Modifier.pointerInput(Unit) {
            awaitEachGesture {
                val down = awaitFirstDown(requireUnconsumed = false)
                // A quick tap never reaches here (long press cancels), so the row's own click
                // still works. A hold enters reorder mode.
                val longPress = awaitLongPressOrCancellation(down.id) ?: return@awaitEachGesture
                val hit = listState.layoutInfo.visibleItemsInfo.firstOrNull {
                    longPress.position.y.toInt() in it.offset..(it.offset + it.size)
                } ?: return@awaitEachGesture

                draggingIndex.value = hit.index
                draggingItem.value = filteredItems.getOrNull(hit.index)
                pointerY.value = longPress.position.y

                // Consume this pointer in the Initial pass (parent-before-child) for the rest of
                // the gesture — including the release — so the row's clickable never treats the
                // long-press-and-release as a tap.
                var dragging = true
                while (dragging) {
                    val event = awaitPointerEvent(PointerEventPass.Initial)
                    val change = event.changes.firstOrNull { it.id == down.id }
                    if (change == null || change.changedToUpIgnoreConsumed()) {
                        change?.consume()
                        dragging = false
                    } else {
                        pointerY.value += change.positionChange().y
                        change.consume()
                        updateAutoScrollSpeed()
                    }
                }
                resetDrag()
            }
        }
    } else {
        Modifier
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth().then(reorderModifier),
    ) {
        searchSettings.searchActionListener.onSearchResults(filteredItems)
        itemsIndexed(filteredItems, key = itemKey) { index, item ->
            val isSelected = item == selectedItem.value
            val isDragging = canReorder && item == draggingItem.value

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
                Modifier
                    .zIndex(if (isDragging) 1f else 0f)
                    .graphicsLayer {
                        if (isDragging) {
                            // Keep the dragged row centered under the finger.
                            val info = listState.layoutInfo.visibleItemsInfo
                                .firstOrNull { it.index == draggingIndex.value }
                            translationY =
                                if (info != null) pointerY.value - (info.offset + info.size / 2f) else 0f
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
