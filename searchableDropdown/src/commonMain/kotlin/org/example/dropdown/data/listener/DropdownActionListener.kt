package org.example.dropdown.data.listener


abstract class DropdownActionListener {
    open fun onExpandListener(isSelected: Boolean) {
        // Default implementation: Do nothing
    }

    open fun <T> onItemSelect(item: T) {
        // Default implementation: Do nothing
    }

    open fun onLongPress() {
        // Default implementation: Do nothing
    }

    open fun onDragStart() {
        // Default implementation: Do nothing
    }
}


val defaultDropdownActionListener = object : DropdownActionListener() {
    override fun onExpandListener(isSelected: Boolean) {
        // Default implementation: Do nothing
    }

    override fun <T> onItemSelect(item: T) {
        // Default implementation: Do nothing
    }

    override fun onLongPress() {
        // Default implementation: Do nothing
    }

    override fun onDragStart() {
        // Default implementation: Do nothing
    }
}

