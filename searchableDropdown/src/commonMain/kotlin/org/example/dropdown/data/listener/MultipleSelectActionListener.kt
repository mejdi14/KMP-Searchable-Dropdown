package org.example.dropdown.data.listener

interface MultipleSelectActionListener<T> {
    fun onSelect(item: T)
    fun onDeselect(item: T)
    fun isSelected(item: T): Boolean
}