package org.example.project.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import org.example.dropdown.data.DefaultDropdownItem
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.SearchSettings
import org.example.dropdown.helper.matchesQuery
import org.example.dropdown.ui.AnimatedIcon
import org.example.dropdown.ui.item.DefaultDropdownItemComposable
import org.example.dropdown.ui.search.SearchArea


@Composable
fun <T : Any> SearchableDropdown(
    items: List<T>,
    searchSettings: SearchSettings<T> = SearchSettings(),
    dropdownConfig: DropdownConfig = DropdownConfig(),
    selectedItem: MutableState<T?> = remember { mutableStateOf<T?>(null) },
    itemContentConfig: ItemContentConfig<T>,
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateDpAsState(targetValue = if (expanded) 180.dp else 0.dp)


    val parentCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }

    Box(
        Modifier
            .fillMaxWidth()
            .background(
                color = dropdownConfig.backgroundColor,
                shape = dropdownConfig.shape
            ).padding(horizontal = dropdownConfig.horizontalPadding)
            .onGloballyPositioned { coordinates ->
                parentCoordinates.value = coordinates
            }
            .clickable {
                expanded = !expanded
            }

    ) {
        if (selectedItem.value != null) {
            when (itemContentConfig) {
                is ItemContentConfig.Custom -> itemContentConfig.content
                is ItemContentConfig.Default -> DefaultDropdownItemComposable(
                    selectedItem.value!!,
                    itemContentConfig.defaultItem
                )
            }
        } else {
            Text(
                text = "Select your skill",
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = 16.dp)
            )
        }
        Box(modifier = Modifier.align(alignment = Alignment.CenterEnd)) {
            AnimatedIcon(rotationAngle, expanded)
        }
    }

    if (expanded) {
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(
                x = 0,
                y = (parentCoordinates.value?.positionInRoot()?.y?.toInt() ?: 0) +
                        (parentCoordinates.value?.size?.height
                            ?: 0) + dropdownConfig.separationSpaceBetweenHeaderAndContent
            ),
            onDismissRequest = {
                expanded = false
            },
            properties = PopupProperties(focusable = true)
        ) {
            AnimatedContent(
                targetState = expanded,
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
                            .width(with(LocalDensity.current) {
                                (parentCoordinates.value?.size?.width?.toDp()
                                    ?: 300.dp) + (dropdownConfig.horizontalPadding * 2)
                            })
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(horizontal = dropdownConfig.horizontalPadding)
                            .animateContentSize()
                    ) {
                        SearchArea(searchQuery, searchSettings)
                        searchSettings.separator
                        LazyColumn(Modifier.fillMaxWidth()) {
                            val filteredItems = if (searchQuery.value.isEmpty()) {
                                items
                            } else {
                                items.filter { item ->
                                    searchSettings.searchProperties.any { prop ->
                                        try {
                                            val value = prop.get(item)?.toString().orEmpty()
                                            value.matchesQuery(
                                                searchQuery.value,
                                                searchSettings.searchType,
                                                searchSettings.ignoreCase
                                            )
                                        } catch (e: Exception) {
                                            false
                                        }
                                    }
                                }
                            }
                            searchSettings.searchActionListener.onSearchResults(filteredItems)

                            items(filteredItems) { item ->
                                Box(Modifier
                                    .clickable {
                                        selectedItem.value = item
                                        expanded = !expanded
                                    }) {
                                    // itemContent(item)
                                    when (itemContentConfig) {
                                        is ItemContentConfig.Custom -> itemContentConfig.content
                                        is ItemContentConfig.Default -> DefaultDropdownItemComposable(
                                            item,
                                            itemContentConfig.defaultItem
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}






