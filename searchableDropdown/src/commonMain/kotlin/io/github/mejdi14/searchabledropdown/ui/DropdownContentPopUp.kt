package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import io.github.mejdi14.searchabledropdown.data.DropdownConfig
import io.github.mejdi14.searchabledropdown.data.search.SearchLocation
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.ItemContentConfig
import io.github.mejdi14.searchabledropdown.helper.filterOperation
import io.github.mejdi14.searchabledropdown.ui.search.SearchArea


@Composable
internal fun <T : Any> DropdownContentPopUp(
    parentCoordinates: MutableState<LayoutCoordinates?>,
    dropdownConfig: DropdownConfig<T>,
    expanded: MutableState<Boolean>,
    searchSettings: SearchSettings<T>,
    items: List<T>,
    selectedItem: MutableState<T?>,
    itemContentConfig: ItemContentConfig<T>,
    selectedItemsList: SnapshotStateList<T>,
    onMove: (Int, Int) -> Unit,
    searchQuery: MutableState<String>,
) {
    val coordinates = parentCoordinates.value
    val anchorLeft = coordinates?.positionInWindow()?.x?.toInt() ?: 0
    val anchorTop = coordinates?.positionInWindow()?.y?.toInt() ?: 0
    val anchorHeight = coordinates?.size?.height ?: 0

    // When the search field is hosted in the header (outside this popup), the popup must not be
    // focusable — a focusable popup treats a tap on the header as an outside tap and dismisses,
    // which would close it the moment you tap the search field. Non-focusable keeps the header
    // field reachable, and we additionally ignore dismiss requests in this mode so no outside-tap
    // path can close it; it is closed via the toggle chevron instead.
    val searchInHeader =
        searchSettings.searchEnabled && searchSettings.searchLocation == SearchLocation.HEADER

    // The current keyboard height (0 when closed). When it opens, the popup's "fits below" check
    // shrinks accordingly and the list flips above the header instead of hiding behind the keyboard.
    val density = LocalDensity.current
    val imeBottomPx = WindowInsets.ime.getBottom(density)
    val windowHeightPx = remember { mutableStateOf(0) }
    val positionProvider =
        remember(anchorLeft, anchorTop, anchorHeight, dropdownConfig.separationSpace, imeBottomPx) {
            DropdownPopupPositionProvider(
                anchorLeftPx = anchorLeft,
                anchorTopPx = anchorTop,
                anchorHeightPx = anchorHeight,
                separationPx = dropdownConfig.separationSpace,
                bottomInsetPx = imeBottomPx,
                onMeasureWindow = { h -> if (windowHeightPx.value != h) windowHeightPx.value = h },
            )
        }

    // Cap the popup height to whichever side (above/below the header, minus the keyboard) has more
    // room, so it never has to overlap the header — keeping the configured separation intact even
    // when it flips above with the keyboard open.
    val maxContentHeight: androidx.compose.ui.unit.Dp = run {
        val wh = windowHeightPx.value
        if (wh == 0) dropdownConfig.maxHeight
        else {
            val sep = dropdownConfig.separationSpace
            val spaceBelow = (wh - imeBottomPx) - (anchorTop + anchorHeight + sep)
            val spaceAbove = anchorTop - sep
            val availablePx = maxOf(spaceBelow, spaceAbove).coerceAtLeast(0)
            minOf(dropdownConfig.maxHeight, with(density) { availablePx.toDp() })
        }
    }
    Popup(
        popupPositionProvider = positionProvider,
        onDismissRequest = {
            if (!searchInHeader) expanded.value = false
        },
        properties = PopupProperties(
            focusable = !searchInHeader,
            dismissOnClickOutside = !searchInHeader,
        )
    ) {
        AnimatedContent(
            targetState = expanded.value,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) + expandVertically(
                    animationSpec = tween(1000),
                    expandFrom = Alignment.Top
                ) togetherWith fadeOut(animationSpec = tween(300))
            }
        ) { isExpanded ->
            if (isExpanded) {
                Column(
                    Modifier
                        .heightIn(max = maxContentHeight)
                        .width(with(LocalDensity.current) {
                            parentCoordinates.value?.size?.width?.toDp() ?: 300.dp
                        })
                        .then(
                            if (dropdownConfig.dropdownShadow.showShadow) {
                                Modifier.shadow(
                                    elevation = dropdownConfig.dropdownShadow.elevation,
                                    shape = dropdownConfig.dropdownShadow.shape
                                )
                            } else Modifier
                        )
                        .background(dropdownConfig.contentBackgroundColor, dropdownConfig.shape)
                        .animateContentSize()
                ) {
                    // Horizontal padding lives on the search area and on each row's content (not on
                    // the whole column) so the rows — and the drag tint — span the full popup width.
                    // The search field is only shown here when it is not being hosted in the header.
                    if (searchSettings.searchEnabled &&
                        searchSettings.searchLocation == SearchLocation.POPUP
                    ) {
                        Column(Modifier.padding(horizontal = dropdownConfig.horizontalPadding)) {
                            SearchArea(searchQuery, searchSettings)
                            searchSettings.separator
                        }
                    }
                    val filteredItems = filterOperation(searchQuery, items, searchSettings)
                    // Reordering only makes sense on the full, unfiltered list.
                    val canReorder = dropdownConfig.reorderEnabled && searchQuery.value.isEmpty()
                    if (filteredItems.isEmpty())
                        dropdownConfig.emptySearchPlaceholder
                    else
                        DropdownItemsList(
                            searchSettings,
                            filteredItems,
                            selectedItem,
                            expanded,
                            itemContentConfig,
                            dropdownConfig,
                            selectedItemsList,
                            canReorder,
                            onMove,
                        )
                }
            }
        }
    }
}

/**
 * Positions the dropdown popup directly next to its anchor (the header):
 *
 * - If the popup fits below the anchor within the window, it opens below with
 *   [separationPx] of spacing.
 * - Otherwise it opens above the anchor (still with [separationPx] of spacing),
 *   growing upward — so it is never pushed far away from the header.
 *
 * Coordinates are absolute within the popup window. Horizontal placement matches
 * the anchor's left edge so the popup lines up with the header (the popup is sized
 * to the header width, so their content padding lines up too).
 */
private class DropdownPopupPositionProvider(
    private val anchorLeftPx: Int,
    private val anchorTopPx: Int,
    private val anchorHeightPx: Int,
    private val separationPx: Int,
    private val bottomInsetPx: Int = 0,
    private val onMeasureWindow: (Int) -> Unit = {},
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        onMeasureWindow(windowSize.height)
        // Exclude the bottom inset (the on-screen keyboard) from the usable height, so once the
        // keyboard is open the popup no longer "fits below" and flips above the header — keeping
        // both the header (kept above the keyboard by imePadding) and the list visible.
        val usableBottom = windowSize.height - bottomInsetPx
        val belowY = anchorTopPx + anchorHeightPx + separationPx
        val fitsBelow = belowY + popupContentSize.height <= usableBottom

        val y = if (fitsBelow) {
            belowY
        } else {
            (anchorTopPx - separationPx - popupContentSize.height).coerceAtLeast(0)
        }
        return IntOffset(x = anchorLeftPx, y = y)
    }
}
