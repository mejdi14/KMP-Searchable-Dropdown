package io.github.mejdi14.searchabledropdown.helper

import androidx.compose.runtime.snapshots.SnapshotStateList
import io.github.mejdi14.searchabledropdown.data.listener.MultipleSelectActionListener

/**
 * Adds [item] to the selection unless it is already present or the selection has
 * reached [maxCount]. A `null` [maxCount] means unlimited.
 */
internal fun <T> MutableList<T>.selectWithLimit(item: T, maxCount: Int?) {
    if (!contains(item) && (maxCount == null || size < maxCount)) {
        add(item)
    }
}

/**
 * Builds a [MultipleSelectActionListener] backed by [selectedItemsList] that honors
 * an optional [maxCount] selection limit.
 */
internal fun <T> multipleSelectActionListener(
    selectedItemsList: SnapshotStateList<T>,
    maxCount: Int?,
): MultipleSelectActionListener<T> = object : MultipleSelectActionListener<T> {
    override fun onSelect(item: T) = selectedItemsList.selectWithLimit(item, maxCount)
    override fun onDeselect(item: T) { selectedItemsList.remove(item) }
    override fun isSelected(item: T): Boolean = selectedItemsList.contains(item)
}
