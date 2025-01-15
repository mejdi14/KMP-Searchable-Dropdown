package org.example.dropdown.data

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class DropdownConfig (
    val backgroundColor: Color = Color.White,
    val shape: Shape = RoundedCornerShape(20.dp),
    val padding: PaddingValues = PaddingValues(horizontal = 30.dp),
)