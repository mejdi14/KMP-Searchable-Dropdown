package org.example.dropdown.data

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DropdownConfig(
    val backgroundColor: Color = Color.White,
    val shape: Shape = RoundedCornerShape(20.dp),
    val dropdownShadow: DropdownShadow = DropdownShadow(shape = shape),
    val horizontalPadding: Dp = 30.dp,
    val separationSpaceBetweenHeaderAndContent: Int = 20,
    val toggleIcon: ToggleIcon = ToggleIcon(),
    val itemSeparator: DropdownItemSeparator = DropdownItemSeparator()
)