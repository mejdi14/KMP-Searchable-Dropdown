package org.example.project.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.DropdownConfig
import org.example.dropdown.data.ItemContentConfig
import org.example.dropdown.data.search.SearchSettings
import org.example.dropdown.ui.DropdownContentPopUp
import org.example.dropdown.ui.ToggleIconComposable
import org.example.dropdown.ui.item.DefaultDropdownItemComposable


@Composable
fun <T : Any> SearchableDropdown(
    items: List<T>,
    searchSettings: SearchSettings<T> = SearchSettings(),
    dropdownConfig: DropdownConfig<T> = DropdownConfig(),
    selectedItem: MutableState<T?> = remember { mutableStateOf<T?>(null) },
    itemContentConfig: ItemContentConfig<T>,
) {
    var expanded = remember { mutableStateOf(false) }
    val rotationAngle by animateDpAsState(targetValue = if (expanded.value) 0.dp else 180.dp)


    val parentCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }

    Box(
        Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = dropdownConfig.shape)
            .background(
                color = dropdownConfig.headerBackgroundColor,
                shape = dropdownConfig.shape
            ).padding(horizontal = dropdownConfig.horizontalPadding)

            .onGloballyPositioned { coordinates ->
                parentCoordinates.value = coordinates
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded.value = !expanded.value
            }

    ) {
        if (selectedItem.value != null) {
            when (itemContentConfig) {
                is ItemContentConfig.Custom -> itemContentConfig.header(selectedItem.value!!, null)
                is ItemContentConfig.Default -> DefaultDropdownItemComposable(
                    selectedItem.value!!,
                    itemContentConfig.defaultItem
                )
            }
        } else {
            dropdownConfig.headerPlaceholder()
        }
        Box(modifier = Modifier.align(alignment = Alignment.CenterEnd)) {
            ToggleIconComposable(rotationAngle, expanded.value, dropdownConfig.toggleIcon)
        }
    }

    if (expanded.value) {
        DropdownContentPopUp(
            parentCoordinates,
            dropdownConfig,
            expanded,
            searchSettings,
            items,
            selectedItem,
            itemContentConfig,
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}







