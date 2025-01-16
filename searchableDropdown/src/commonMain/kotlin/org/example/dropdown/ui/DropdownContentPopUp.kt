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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.helper.filterOperation
import org.example.dropdown.ui.search.SearchArea


@Composable
internal fun <T : Any> DropdownContentPopUp(
    parentCoordinates: MutableState<LayoutCoordinates?>,
    dropdownConfig: DropdownConfig,
    expanded: MutableState<Boolean>,
    searchSettings: SearchSettings<T>,
    items: List<T>,
    selectedItem: MutableState<T?>,
    itemContentConfig: ItemContentConfig<T>
) {
    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(
            x = 0,
            y = (parentCoordinates.value?.positionInRoot()?.y?.toInt() ?: 0) +
                    (parentCoordinates.value?.size?.height
                        ?: 0) + dropdownConfig.separationSpaceBetweenHeaderAndContent
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
                        .height(dropdownConfig.maxHeight)
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
                        .shadow(elevation = 2.dp, shape = dropdownConfig.shape)
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .padding(horizontal = dropdownConfig.horizontalPadding)
                        .animateContentSize()
                ) {
                    SearchArea(searchQuery, searchSettings)
                    searchSettings.separator
                    val filteredItems = filterOperation(searchQuery, items, searchSettings)
                    if (filteredItems.isEmpty())
                        dropdownConfig.emptySearchPlaceholder
                    else
                        DropdownItemsList(
                            searchSettings,
                            filteredItems,
                            selectedItem,
                            expanded,
                            itemContentConfig,
                            items,
                            dropdownConfig
                        )
                }
            }
        }
    }
}
