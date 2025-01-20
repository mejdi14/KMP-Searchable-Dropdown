package org.example.dropdown.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.data.selection.ItemContentConfig
import org.example.dropdown.helper.filterOperation
import org.example.dropdown.ui.search.SearchArea


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
) {
    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(
            x = 0,
            y = (parentCoordinates.value?.positionInRoot()?.y?.toInt() ?: 0) +
                    (parentCoordinates.value?.size?.height
                        ?: 0) + dropdownConfig.separationSpace
        ),
        onDismissRequest = {
            expanded.value = false
        },
        properties = PopupProperties(focusable = true)
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
                var searchQuery = remember { mutableStateOf("") }
                Column(
                    Modifier
                        .heightIn(max = dropdownConfig.maxHeight)
                        .width(with(LocalDensity.current) {
                            (parentCoordinates.value?.size?.width?.toDp()
                                ?: 300.dp) + (dropdownConfig.horizontalPadding * 2)
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
                        .padding(horizontal = dropdownConfig.horizontalPadding)
                        .animateContentSize()
                ) {
                    if (searchSettings.searchEnabled){
                        SearchArea(searchQuery, searchSettings)
                        searchSettings.separator
                    }
                    var filteredItems = filterOperation(searchQuery, items, searchSettings)
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
                            selectedItemsList
                        )
                }
            }
        }
    }
}
