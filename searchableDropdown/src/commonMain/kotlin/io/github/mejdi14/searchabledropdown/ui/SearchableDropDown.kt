package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.listener.MultipleRemoveItemListener
import io.github.mejdi14.searchabledropdown.data.search.SearchLocation
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.ItemContentConfig
import io.github.mejdi14.searchabledropdown.data.selection.MultipleItemContentConfig
import io.github.mejdi14.searchabledropdown.data.selection.SingleItemContentConfig
import io.github.mejdi14.searchabledropdown.ui.item.DefaultSingleItemComposable
import io.github.mejdi14.searchabledropdown.ui.search.SearchArea


@Composable
fun <T : Any> SearchableDropdown(
    items: List<T>,
    searchSettings: SearchSettings<T> = SearchSettings(),
    dropdownConfig: DropdownConfig<T> = DropdownConfig(),
    selectedItem: MutableState<T?> = remember { mutableStateOf<T?>(null) },
    itemContentConfig: ItemContentConfig<T>,
) {
    val expanded = remember { mutableStateOf(false) }
    val rotationAngle by animateDpAsState(targetValue = if (expanded.value) 0.dp else 180.dp)
    val selectedItemsList = remember { mutableStateListOf<T>() }

    val parentCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }

    // A reorderable copy of the items, used only when drag-to-reorder is enabled. It is
    // reseeded whenever the caller passes a new source list, but a reorder (which does not
    // change [items]) is preserved.
    val orderedItems = remember { mutableStateListOf<T>().apply { addAll(items) } }
    LaunchedEffect(items) {
        orderedItems.clear()
        orderedItems.addAll(items)
    }
    val displayedItems: List<T> = if (dropdownConfig.reorderEnabled) orderedItems else items
    val onMove: (Int, Int) -> Unit = { from, to ->
        if (from in orderedItems.indices && to in orderedItems.indices && from != to) {
            orderedItems.add(to, orderedItems.removeAt(from))
            dropdownConfig.onReorder(orderedItems.toList())
        }
    }

    // Search query lifted here so it can be shared between the header (when the search field lives
    // there) and the popup's filtering. Cleared on close so each open starts fresh.
    val searchQuery = remember { mutableStateOf("") }
    LaunchedEffect(expanded.value) {
        if (!expanded.value) searchQuery.value = ""
    }
    val showHeaderSearch = searchSettings.searchEnabled &&
            searchSettings.searchLocation == SearchLocation.HEADER && expanded.value


    Row(
        Modifier
            .fillMaxWidth()
            .height(dropdownConfig.headerHeight)
            .shadow(
                elevation = dropdownConfig.dropdownShadow.elevation,
                shape = dropdownConfig.shape
            )

            .background(
                color = dropdownConfig.headerBackgroundColor,
                shape = dropdownConfig.shape
            )
            // Capture the full header box (before padding) so the popup can match
            // its left edge and width exactly.
            .onGloballyPositioned { coordinates ->
                parentCoordinates.value = coordinates
            }
            .padding(horizontal = dropdownConfig.horizontalPadding)
            // Tapping the header toggles the popup — except while it is acting as the search field,
            // where taps should reach the text field instead of closing the popup.
            .then(
                if (showHeaderSearch) Modifier
                else Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded.value = !expanded.value
                }
            ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        if (showHeaderSearch) {
            Box(Modifier.weight(1f)) {
                SearchArea(searchQuery, searchSettings)
            }
        } else {
            DropdownHeaderContent(
                itemContentConfig = itemContentConfig,
                selectedItem = selectedItem,
                selectedItemsList = selectedItemsList,
                placeholder = dropdownConfig.headerPlaceholder,
            )
        }
        Spacer(Modifier.width(5.dp))

        Box(
            modifier = Modifier.size(20.dp).clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded.value = !expanded.value
            }
        ) {
            ToggleIconComposable(
                rotationAngle, expanded.value, dropdownConfig.toggleIcon, Modifier.align(
                    Alignment.Center
                )
            )
        }
    }

    if (expanded.value) {
        DropdownContentPopUp(
            parentCoordinates,
            dropdownConfig,
            expanded,
            searchSettings,
            displayedItems,
            selectedItem,
            itemContentConfig,
            selectedItemsList,
            onMove,
            searchQuery,
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

/**
 * Renders the header preview of the current selection.
 *
 * - Single selection: shows [selectedItem] when set, otherwise the [placeholder].
 * - Multiple selection: shows a horizontally scrolling list of [selectedItemsList],
 *   otherwise the [placeholder].
 */
@Composable
private fun <T : Any> RowScope.DropdownHeaderContent(
    itemContentConfig: ItemContentConfig<T>,
    selectedItem: MutableState<T?>,
    selectedItemsList: SnapshotStateList<T>,
    placeholder: @Composable () -> Unit,
) {
    when (itemContentConfig) {
        is SingleItemContentConfig -> {
            val selected = selectedItem.value
            Box(Modifier.weight(1f)) {
                if (selected == null) {
                    placeholder()
                } else when (itemContentConfig) {
                    is SingleItemContentConfig.Custom ->
                        itemContentConfig.header(selected, null)

                    is SingleItemContentConfig.Default ->
                        DefaultSingleItemComposable(selected, itemContentConfig.defaultItem)
                }
            }
        }

        is MultipleItemContentConfig -> {
            if (selectedItemsList.isEmpty()) {
                Box(Modifier.weight(1f)) { placeholder() }
            } else {
                val removeItemListener = remember(selectedItemsList) {
                    object : MultipleRemoveItemListener<T> {
                        override fun onRemove(item: T) {
                            selectedItemsList.remove(item)
                        }
                    }
                }
                LazyRow(
                    modifier = Modifier.weight(1f).padding(vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 6.dp)
                ) {
                    items(selectedItemsList) { currentItem ->
                        when (itemContentConfig) {
                            is MultipleItemContentConfig.Custom ->
                                itemContentConfig.header(currentItem, null, removeItemListener)

                            is MultipleItemContentConfig.Default ->
                                DefaultSingleItemComposable(
                                    currentItem,
                                    itemContentConfig.defaultItemCustomization
                                )
                        }
                    }
                }
            }
        }
    }
}



