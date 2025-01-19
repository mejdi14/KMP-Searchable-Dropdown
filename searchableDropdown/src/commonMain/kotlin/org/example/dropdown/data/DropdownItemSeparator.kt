package org.example.dropdown.data

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.dropdown.data.base.BaseSeparator

data class DropdownItemSeparator(
    override val showSeparator: Boolean = false,
    override val color: Color = Color.Black,
    override val height: Dp = 0.3.dp,
    override val modifier: Modifier = Modifier.fillMaxWidth()
) : BaseSeparator()

