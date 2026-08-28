package io.github.mejdi14.searchabledropdown.data.listener

abstract class DropdownActionListener {
    open fun onExpandListener(isSelected: Boolean) {

    }

    open fun <T> onItemSelect(item: T) {

    }

    open fun onLongPress() {

    }

    open fun onDragStart() {

    }
}

val defaultDropdownActionListener = object : DropdownActionListener() {}
