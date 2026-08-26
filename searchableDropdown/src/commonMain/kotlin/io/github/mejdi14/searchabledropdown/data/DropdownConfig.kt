package io.github.mejdi14.searchabledropdown.data

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mejdi14.searchabledropdown.data.listener.DropdownActionListener
import io.github.mejdi14.searchabledropdown.data.listener.defaultDropdownActionListener
import io.github.mejdi14.searchabledropdown.ui.EmptySearchPlaceholder

data class DropdownConfig<T>(
    val headerBackgroundColor: Color = Color.White,
    val headerHeight: Dp = 60.dp,
    val contentBackgroundColor: Color = Color.White,
    val shape: Shape = RoundedCornerShape(20.dp),
    val maxHeight: Dp = 300.dp,
    val dropdownShadow: DropdownShadow = DropdownShadow(shape = shape),
    val horizontalPadding: Dp = 30.dp,
    val headerPlaceholder: @Composable ()-> Unit = {
        Text(
            text = "Select your skill",
            color = Color.Black,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
    },
    val withItemSelection: Boolean = true,
    val separationSpace: Int = 20,
    val toggleIcon: ToggleIcon = ToggleIcon(),
    val itemSeparator: DropdownItemSeparator = DropdownItemSeparator(),
    val emptySearchPlaceholder: @Composable () -> Unit = {
        EmptySearchPlaceholder()
    },
    val dropdownActionListener: DropdownActionListener = defaultDropdownActionListener,
    /**
     * When true, items can be reordered by long-pressing and dragging them while the
     * popup is open. Reordering is only active when the list is not being filtered by
     * a search query.
     */
    val reorderEnabled: Boolean = false,
    /**
     * Produces a stable, unique key for each item, used to track rows while reordering.
     * On Android the key must be a type that can be stored in a Bundle (e.g. String, Int),
     * so provide a value like `{ it.id }` when the default [hashCode] is not unique enough.
     */
    val reorderKey: (T) -> Any = { it.hashCode() },
    /**
     * Called with the new item order after a drag-and-drop reorder completes. Useful
     * for persisting the order on the caller side.
     */
    val onReorder: (List<T>) -> Unit = {}
)