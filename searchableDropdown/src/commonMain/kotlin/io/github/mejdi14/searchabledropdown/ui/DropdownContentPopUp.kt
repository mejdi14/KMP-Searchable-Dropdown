package io.github.mejdi14.searchabledropdown.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import io.github.mejdi14.searchabledropdown.data.PopupPlacement
import io.github.mejdi14.searchabledropdown.data.search.SearchLocation
import io.github.mejdi14.searchabledropdown.data.search.SearchSettings
import io.github.mejdi14.searchabledropdown.data.selection.ItemContentConfig
import io.github.mejdi14.searchabledropdown.helper.filterOperation
import io.github.mejdi14.searchabledropdown.ui.search.SearchArea

@Composable
internal fun <T : Any> DropdownContentPopUp(
    anchorBounds: IntRect,
    dropdownConfig: DropdownConfig<T>,
    expanded: MutableState<Boolean>,
    searchSettings: SearchSettings<T>,
    items: List<T>,
    selectedItem: MutableState<T?>,
    itemContentConfig: ItemContentConfig<T>,
    selectedItemsList: SnapshotStateList<T>,
    onMove: (Int, Int) -> Unit,
    searchQuery: MutableState<String>,
    windowHeightPx: Int,
) {
    val anchorLeft = anchorBounds.left
    val anchorTop = anchorBounds.top
    val anchorBottom = anchorBounds.bottom
    val anchorHeight = anchorBounds.height
    val sep = dropdownConfig.separationSpace

    val searchInHeader =
        searchSettings.searchEnabled && searchSettings.searchLocation == SearchLocation.HEADER

    val density = LocalDensity.current

    val imeBottomPx = WindowInsets.ime.getBottom(density)
    val imeVisible = imeBottomPx > 0

    val maxHeightPx = with(density) { dropdownConfig.maxHeight.roundToPx() }
    val spaceAbovePx = (anchorTop - sep).coerceAtLeast(0)
    val spaceBelowPx = if (windowHeightPx > 0) {
        (windowHeightPx - imeBottomPx - anchorBottom - sep).coerceAtLeast(0)
    } else {
        maxHeightPx
    }

    val minUsablePx = with(density) { 120.dp.roundToPx() }
    val openAbove = imeVisible && when (dropdownConfig.popupPlacement) {
        PopupPlacement.ABOVE -> true
        PopupPlacement.BELOW -> false
        PopupPlacement.AUTO -> spaceBelowPx < minUsablePx && spaceAbovePx > spaceBelowPx
    }
    val availablePx = when {
        !imeVisible -> maxHeightPx
        openAbove -> spaceAbovePx
        else -> spaceBelowPx
    }
    val fixedWindowHeightPx = if (imeVisible) availablePx else minOf(maxHeightPx, availablePx)
    val animatedHeightPx by animateIntAsState(
        targetValue = fixedWindowHeightPx,
        animationSpec = tween(durationMillis = 160),
        label = "popupHeight",
    )
    val maxContentHeight = with(density) { animatedHeightPx.toDp() }

    val positionProvider = remember(
        anchorLeft, anchorTop, anchorBottom, anchorHeight, sep, imeVisible, openAbove,
        animatedHeightPx,
    ) {
        DropdownPopupPositionProvider(
            anchorLeftPx = anchorLeft,
            anchorTopPx = anchorTop,
            anchorHeightPx = anchorHeight,
            separationPx = sep,
            openAbove = openAbove,
            fixedAboveHeightPx = animatedHeightPx,
            forcePlacement = imeVisible,
        )
    }

    val popupWidth = with(density) {
        if (anchorBounds.width > 0) anchorBounds.width.toDp() else 300.dp
    }

    val card: @Composable () -> Unit = {
        Column(
            Modifier
                .heightIn(max = maxContentHeight)
                .width(popupWidth)
                .then(
                    if (dropdownConfig.dropdownShadow.showShadow) {
                        Modifier.shadow(
                            elevation = dropdownConfig.dropdownShadow.elevation,
                            shape = dropdownConfig.dropdownShadow.shape
                        )
                    } else Modifier
                )
                .background(dropdownConfig.contentBackgroundColor, dropdownConfig.shape)
                .clip(dropdownConfig.shape)
                .animateContentSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) {}
        ) {

            if (searchSettings.searchEnabled &&
                searchSettings.searchLocation == SearchLocation.POPUP
            ) {
                Column(Modifier.padding(horizontal = dropdownConfig.horizontalPadding)) {
                    SearchArea(searchQuery, searchSettings)
                    searchSettings.separator
                }
            }
            val filteredItems = filterOperation(searchQuery, items, searchSettings)

            val canReorder = dropdownConfig.reorderEnabled && searchQuery.value.isEmpty()
            Box(Modifier.weight(1f, fill = false)) {
                if (filteredItems.isEmpty())
                    dropdownConfig.emptySearchPlaceholder()
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
            dropdownConfig.footer()
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
        val dismissInteraction = remember { MutableInteractionSource() }
        Box(
            modifier = Modifier
                .width(popupWidth)
                .then(
                    if (imeVisible) {
                        Modifier
                            .height(maxContentHeight)
                            .clickable(
                                interactionSource = dismissInteraction,
                                indication = null,
                            ) { expanded.value = false }
                    } else Modifier
                ),
            contentAlignment = if (imeVisible && openAbove) Alignment.BottomStart else Alignment.TopStart,
        ) {
            card()
        }
    }
}

private class DropdownPopupPositionProvider(
    private val anchorLeftPx: Int,
    private val anchorTopPx: Int,
    private val anchorHeightPx: Int,
    private val separationPx: Int,
    private val openAbove: Boolean = false,
    private val fixedAboveHeightPx: Int = 0,
    private val forcePlacement: Boolean = false,
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        val belowY = anchorTopPx + anchorHeightPx + separationPx

        if (forcePlacement) {
            val y = if (openAbove) {
                (anchorTopPx - separationPx - fixedAboveHeightPx).coerceAtLeast(0)
            } else {
                belowY
            }
            return IntOffset(x = anchorLeftPx, y = y)
        }

        if (openAbove) {
            val y = (anchorTopPx - separationPx - fixedAboveHeightPx).coerceAtLeast(0)
            return IntOffset(x = anchorLeftPx, y = y)
        }

        val fitsBelow = belowY + popupContentSize.height <= windowSize.height
        val y = if (fitsBelow) {
            belowY
        } else {
            (anchorTopPx - separationPx - popupContentSize.height).coerceAtLeast(0)
        }
        return IntOffset(x = anchorLeftPx, y = y)
    }
}
