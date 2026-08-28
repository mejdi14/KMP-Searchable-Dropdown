package io.github.mejdi14.searchabledropdown.helper

import androidx.compose.runtime.snapshots.SnapshotStateList
import io.github.mejdi14.searchabledropdown.data.listener.MultipleSelectActionListener

internal fun <T> MutableList<T>.selectWithLimit(item: T, maxCount: Int?) {
    if (!contains(item) && (maxCount == null || size < maxCount)) {
        add(item)
    }
}

internal fun <T> multipleSelectActionListener(
    selectedItemsList: SnapshotStateList<T>,
    maxCount: Int?,
): MultipleSelectActionListener<T> = object : MultipleSelectActionListener<T> {
    override fun onSelect(item: T) = selectedItemsList.selectWithLimit(item, maxCount)
    override fun onDeselect(item: T) { selectedItemsList.remove(item) }
    override fun isSelected(item: T): Boolean = selectedItemsList.contains(item)
}
