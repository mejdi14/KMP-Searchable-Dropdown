package io.github.mejdi14.searchabledropdown.data.listener

interface MultipleSelectActionListener<T> {
    fun onSelect(item: T)
    fun onDeselect(item: T)
    fun isSelected(item: T): Boolean
}